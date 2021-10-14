package com.example.geolocationdatasaver.geolocation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
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

    @GetMapping
    public List<GeolocationDto> getAllGeolocations() {
        List<Geolocation> geolocations = geolocationService.getAllGeolocations();
        return geolocations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GeolocationDto createGeolocation(@Valid @RequestBody GeolocationDto geolocationDto) throws ParseException {
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
