package com.example.geolocationdatasaver.geolocation.service;

import com.example.geolocationdatasaver.geolocation.model.Geolocation;
import com.example.geolocationdatasaver.geolocation.repository.GeolocationRepository;
import com.example.geolocationdatasaver.geolocation.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeolocationService {

    private final GeolocationRepository geolocationRepository;

    public GeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    public Geolocation getGeolocation(Long id) {
        return geolocationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Product with id " + id + " does not exist");
        });
    }

    public List<Geolocation> getAllGeolocations() {
        return geolocationRepository.findAll();
    }

    public Geolocation addOrUpdateGeolocation(Geolocation currentGeolocation) {
        String deviceId = currentGeolocation.getDeviceId();
        if (geolocationRepository.selectExistsDeviceId(deviceId)) {
            Geolocation geolocationToUpdate = geolocationRepository.findByDeviceId(deviceId);
            geolocationToUpdate.setLatitude(currentGeolocation.getLatitude());
            geolocationToUpdate.setLongitude(currentGeolocation.getLongitude());
            return geolocationRepository.save(geolocationToUpdate);
        }
        return geolocationRepository.save(currentGeolocation);
    }
}
