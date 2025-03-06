package com.wego.iwan.threads;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wego.iwan.bean.ApiResponse;
import com.wego.iwan.bean.CarparkData;
import com.wego.iwan.bean.CarparkInfo;
import com.wego.iwan.bean.Item;
import com.wego.iwan.model.CarPark;
import com.wego.iwan.model.ParkInfo;
import com.wego.iwan.repository.CarparkRepository;
import com.wego.iwan.repository.ParkInfoRepository;
import com.wego.iwan.utils.SVY21Converter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class CarparkScheduler {

    @Value("${carpark.api.url}")
    private String apiUrl;

    @Value("${carpark.api.canFetch}")
    private boolean canFetch;

    private final ParkInfoRepository parkInforepository;
    private final CarparkRepository carparkRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final HashMap<String, CarPark> carparkMap = new HashMap<>();

    @Scheduled(cron = "${carpark.cron}")
    public void fetchCarparkAvailability() {
        System.out.println("Fetching carpark availability...");

        if (!canFetch) {
            System.out.println("Fetching not allowed...");
            return;
        }

        int retryCount = 0;
        while (retryCount < 3) {
            try {
                System.out.println("Attempt " + (retryCount + 1) + " to fetch carpark data...");
                ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);

                if (response != null && response.getItems() != null) {
                    System.out.println("Carpark data found, delete existing data..please wait...");
                    parkInforepository.deleteAll();
                    
                    for (Item item : response.getItems()) {
                        int index = 0;
                        for (CarparkData data : item.getCarparkData()) {
                            index++;
                            System.out.println("Processing item " + index + " / " + item.getCarparkData().length);
                            CarPark mapCarPark = carparkMap.get(data.getCarparkNumber());
                            double[] latLon = new double[2];
                            latLon[0] = 0;
                            latLon[1] = 0;
                            String address = "";

                            if (mapCarPark == null) {
                                Optional<CarPark> carpark = carparkRepository.findByCarParkNo(data.getCarparkNumber());
                                if (carpark.isPresent()) {
                                    carparkMap.put(data.getCarparkNumber(), carpark.get());
                                    mapCarPark = carpark.get();
                                    address = carpark.get().getAddress();
                                }
                            }

                            if (mapCarPark != null) {
                                latLon = SVY21Converter.svy21ToLatLon(mapCarPark.getXCoord(), mapCarPark.getYCoord());
                            }

                            for (CarparkInfo info : data.getCarparkInfo()) {
                                ParkInfo parkInfo = new ParkInfo(
                                        data.getCarparkNumber(),
                                        address,
                                        Integer.parseInt(info.getTotalLots()),
                                        Integer.parseInt(info.getLotsAvailable()),
                                        info.getLotType(),
                                        latLon[0], latLon[1],
                                        LocalDateTime.parse(data.getUpdateDatetime())
                                );
                                parkInforepository.save(parkInfo);
                            }
                        }
                    }
                }

                System.out.println("Carpark availability successfully fetched.");
                return; 

            } catch (Exception e) {
                if (e.getMessage().contains("Connection reset")) {
                    System.out.println("Connection reset error, retrying... (" + (retryCount + 1) + "/3)");
                    retryCount++;
                    try {
                        Thread.sleep(5000); 
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        System.out.println("Retry sleep interrupted.");
                        return;
                    }
                } else {
                    System.out.println("Error fetching carpark availability: " + e.getMessage());
                    return;
                }
            }
        }

        System.out.println("Failed to fetch carpark availability after 3 attempts.");
    }
}
