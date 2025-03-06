package com.wego.iwan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wego.iwan.model.CarPark;

@Repository
public interface CarparkRepository extends JpaRepository<CarPark, String> {
    Optional<CarPark> findByCarParkNo(String carParkNo);
}
