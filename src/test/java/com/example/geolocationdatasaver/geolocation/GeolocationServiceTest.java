package com.example.geolocationdatasaver.geolocation;

import com.example.geolocationdatasaver.geolocation.exception.BadRequestException;
import com.example.geolocationdatasaver.geolocation.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class GeolocationServiceTest {

    @Mock
    private GeolocationRepository geolocationRepository;
    private GeolocationService underTest;

    @Captor
    private ArgumentCaptor<Geolocation> geolocationArgumentCaptor;

    @BeforeEach
    void setUp() {
        underTest = new GeolocationService(geolocationRepository);
    }

    @Test
    void getAllGeolocations_listNotEmpty_thrownNotFoundException() {
        //given
        Geolocation geo1 = new Geolocation("12345", 505430, 1423412);
        Geolocation geo2 = new Geolocation("67891", -34, 41.40338);
        Geolocation geo3 = new Geolocation("23456", 1, 2);

        List<Geolocation> expected = Arrays.asList(geo1, geo2, geo3);
        given(geolocationRepository.findAll()).willReturn(expected);

        //when
        final List<Geolocation> geolocations = underTest.getAllGeolocations();

        // then
        assertThat(geolocations).isEqualTo(expected);
    }

    @Test
    void getAllGeolocations_emptyList_thrownNotFoundException() {
        //given
        given(geolocationRepository.findAll()).willReturn(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> underTest.getAllGeolocations())
                .isInstanceOf(NotFoundException.class)
                .hasMessage("The geolocation list is empty");
    }

    @Test
    void addGeolocation_deviceIdNotExist_geolocationSaved() {
        //given
        Geolocation geolocation = new Geolocation("12345", 505430, 1423412);

        //when
        underTest.addGeolocation(geolocation);

        //then
        then(geolocationRepository).should().save(geolocationArgumentCaptor.capture());
        Geolocation geolocationArgumentCaptorValue = geolocationArgumentCaptor.getValue();
        assertThat(geolocationArgumentCaptorValue).isEqualTo(geolocation);
    }

    @Test
    void addGeolocation_deviceIdExists_thrownBadRequestException() {
        //given
        String deviceId = "12345";
        Geolocation geolocation = new Geolocation(deviceId, 505430, 1423412);
        given(geolocationRepository.selectExistsDeviceId(deviceId)).willReturn(true);

        //when / then
        assertThatThrownBy(() -> underTest.addGeolocation(geolocation))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Device with id = " + deviceId + " already exists");
    }

    @Test
    void updateGeolocation_deviceIdNotExists_thrownBadRequestException() {
        //given
        String deviceId = "12345";
        Geolocation geolocation = new Geolocation(deviceId, 505430, 1423412);
        //when / then
        assertThatThrownBy(() -> underTest.updateGeolocation(geolocation))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Device with id = " + deviceId + " does not exist");
    }
}