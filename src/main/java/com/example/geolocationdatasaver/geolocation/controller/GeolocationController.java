package com.example.geolocationdatasaver.geolocation.controller;

import com.example.geolocationdatasaver.geolocation.controller.dto.GeolocationDto;
import com.example.geolocationdatasaver.geolocation.model.Geolocation;
import com.example.geolocationdatasaver.geolocation.service.GeolocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/geolocations")
public class GeolocationController {

    private final GeolocationService geolocationService;
    private final ModelMapper modelMapper;

    @Autowired
    public GeolocationController(GeolocationService geolocationService, ModelMapper modelMapper) {
        this.geolocationService = geolocationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public Geolocation getGeolocation(@PathVariable Long id) {
        return geolocationService.getGeolocation(id);
    }

    @GetMapping
    public List<GeolocationDto> getAllGeolocations() {
        return geolocationService.getAllGeolocations()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public GeolocationDto addOrUpdateGeolocation(@Valid @RequestBody GeolocationDto geolocationDto) {
        Geolocation geolocation = convertToEntity(geolocationDto);
        Geolocation geolocationCreated = geolocationService.addOrUpdateGeolocation(geolocation);
        return convertToDto(geolocationCreated);
    }

    private GeolocationDto convertToDto(Geolocation geolocation) {
        return modelMapper.map(geolocation, GeolocationDto.class);
    }

    private Geolocation convertToEntity(GeolocationDto geolocationDto) {
        return modelMapper.map(geolocationDto, Geolocation.class);
    }
}
