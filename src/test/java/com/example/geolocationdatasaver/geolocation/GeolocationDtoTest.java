package com.example.geolocationdatasaver.geolocation;

import com.example.geolocationdatasaver.geolocation.controller.dto.GeolocationDto;
import com.example.geolocationdatasaver.geolocation.model.Geolocation;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeolocationDtoTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void convertGeolocationEntityToGeolocationDto_convertedCorrectly() {
        //given
        Geolocation geolocation = new Geolocation("12345", 505430D, 1423412D);

        //when
        GeolocationDto geolocationDto = modelMapper.map(geolocation, GeolocationDto.class);

        //then
        assertEquals(geolocation.getDeviceId(), geolocationDto.getDeviceId());
        assertEquals(geolocation.getLatitude(), geolocationDto.getLatitude());
        assertEquals(geolocation.getLongitude(), geolocationDto.getLongitude());
    }

    @Test
    public void convertGeolocationDtoToGeolocationEntity_convertedCorrectly() {
        //given
        GeolocationDto geolocationDto = new GeolocationDto("12345", 505430D, 1423412D);

        //when
        Geolocation geolocation = modelMapper.map(geolocationDto, Geolocation.class);

        //then
        assertEquals(geolocationDto.getDeviceId(), geolocation.getDeviceId());
        assertEquals(geolocationDto.getLatitude(), geolocation.getLatitude());
        assertEquals(geolocationDto.getLongitude(), geolocation.getLongitude());
    }
}
