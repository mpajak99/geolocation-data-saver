package com.example.geolocationdatasaver.geolocation.repository;

import com.example.geolocationdatasaver.geolocation.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {

    @Query("" +
            "SELECT CASE WHEN (COUNT(g) > 0) THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Geolocation g " +
            "WHERE g.deviceId = :deviceId"
    )
    Boolean selectExistsDeviceId(@Param("deviceId") String deviceId);

    Geolocation findByDeviceId(String deviceId);
}