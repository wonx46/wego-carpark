package com.wego.iwan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wego.iwan.model.ParkInfo;

@Repository
public interface ParkInfoRepository extends JpaRepository<ParkInfo, Long> {
  
  @Query("SELECT p FROM ParkInfo p ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * cos(radians(p.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(p.latitude)))) ASC")
  Page<ParkInfo> findNearestCarparks(@Param("latitude") Double latitude, @Param("longitude") Double longitude, Pageable pageable);
}

