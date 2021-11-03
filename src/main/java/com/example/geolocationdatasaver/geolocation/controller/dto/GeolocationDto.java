package com.example.geolocationdatasaver.geolocation.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationDto {

    @NotBlank(message = "Device Id cannot be empty or null")
    private String deviceId;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    private Date lastUpdateDate;

    public GeolocationDto(String deviceId, Double latitude, Double longitude) {
        this.deviceId = deviceId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
