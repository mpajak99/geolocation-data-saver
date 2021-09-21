package com.example.geolocationdatasaver.geolocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/geolocations")
public class GeolocationController {

    private final GeolocationService geolocationService;

    @Autowired
    public GeolocationController(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @GetMapping
    public List<Geolocation> getAllGeolocations() {
        return geolocationService.getAllGeolocations();
    }

    @PostMapping
    public String addGeolocation(@Valid @RequestBody Geolocation geolocation) {
        return geolocationService.addGeolocation(geolocation);
    }

    @PutMapping
    public String updateGeolocation(@Valid @RequestBody Geolocation geolocation) {
        return geolocationService.updateGeolocation(geolocation);
    }
}
