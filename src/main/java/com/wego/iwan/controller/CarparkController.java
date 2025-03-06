package com.wego.iwan.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wego.iwan.bean.ParkAvail;
import com.wego.iwan.repository.ParkInfoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carparks")
class CarparkController {

    private final ParkInfoRepository repository;

    @GetMapping("/v1/nearest")
    public ResponseEntity<List<ParkAvail>> getNearestCarparks(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam int page,
            @RequestParam int per_page
    ) {
        if(latitude == null || longitude == null) {
            return ResponseEntity.badRequest().build();
        }
        Pageable pageable = PageRequest.of(page - 1, per_page, Sort.by("id"));
        return ResponseEntity.ok(repository.findNearestCarparks(latitude, longitude, pageable).map(parkInfo -> new ParkAvail(parkInfo)).getContent());
    }
}