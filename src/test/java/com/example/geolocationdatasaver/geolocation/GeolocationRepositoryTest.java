package com.example.geolocationdatasaver.geolocation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GeolocationRepositoryTest {

    @Autowired
    private GeolocationRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void selectExistsDeviceId_deviceIdExists_returnTrue() {
        //given
        String deviceId = "12345";
        Geolocation geolocation = new Geolocation(deviceId, 505430D, 1423412D);
        underTest.save(geolocation);

        //when
        boolean expected = underTest.selectExistsDeviceId(deviceId);

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void selectExistsDeviceId_deviceIdNotExist_returnFalse() {
        //given
        String deviceId = "12345";

        //when
        boolean expected = underTest.selectExistsDeviceId(deviceId);

        //then
        assertThat(expected).isFalse();
    }
}
