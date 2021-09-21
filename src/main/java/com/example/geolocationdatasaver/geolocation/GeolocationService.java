package com.example.geolocationdatasaver.geolocation;

import com.example.geolocationdatasaver.geolocation.exception.BadRequestException;
import com.example.geolocationdatasaver.geolocation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeolocationService {

    private final GeolocationRepository geolocationRepository;

    @Autowired
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

    public String addGeolocation(Geolocation geolocation) {
        String deviceId = geolocation.getDeviceId();
        if (geolocationRepository.selectExistsDeviceId(deviceId)) {
            throw new BadRequestException("Device with id = " + deviceId + " already exists");
        }

        geolocationRepository.save(geolocation);
        return "Successfully added";
    }

    public String updateGeolocation(Geolocation currentGeolocation) {
        String deviceId = currentGeolocation.getDeviceId();
        if (!geolocationRepository.selectExistsDeviceId(deviceId)) {
            throw new BadRequestException("Device with id = " + deviceId + " does not exist");
        }

        Geolocation geolocation = geolocationRepository.findByDeviceId(deviceId);

        geolocation.setLatitude(currentGeolocation.getLatitude());
        geolocation.setLongitude(currentGeolocation.getLongitude());

        geolocationRepository.save(geolocation);
        return "Successfully updated";
    }
}
