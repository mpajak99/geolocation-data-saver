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

    public String updateGeolocation(Geolocation geolocation) {
        String deviceId = geolocation.getDeviceId();
        if (!geolocationRepository.selectExistsDeviceId(deviceId)) {
            throw new NotFoundException("Device with id = " + deviceId + " does not exist");
        }

        Geolocation oldGeo = geolocationRepository.findByDeviceId(deviceId);

        oldGeo.setLatitude(geolocation.getLatitude());
        oldGeo.setLongitude(geolocation.getLongitude());

        geolocationRepository.save(oldGeo);
        return "Successfully updated";
    }
}
