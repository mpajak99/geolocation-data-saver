package com.example.geolocationdatasaver.geolocation;

import com.example.geolocationdatasaver.geolocation.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeolocationService {

    private final GeolocationRepository geolocationRepository;

    public GeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    public List<Geolocation> getAllGeolocations() {
        final List<Geolocation> allGeolocations = geolocationRepository.findAll();

        if (allGeolocations.isEmpty()) {
            throw new NotFoundException("The geolocation list is empty");
        }
        return allGeolocations;
    }

    public Geolocation addOrUpdateGeolocation(Geolocation currentGeolocation) {
        String deviceId = currentGeolocation.getDeviceId();

        if (geolocationRepository.selectExistsDeviceId(deviceId)) {
            Geolocation geolocation = geolocationRepository.findByDeviceId(deviceId);
            geolocation.setLatitude(currentGeolocation.getLatitude());
            geolocation.setLongitude(currentGeolocation.getLongitude());
            return geolocationRepository.save(geolocation);
        }
        return geolocationRepository.save(currentGeolocation);
    }
}
