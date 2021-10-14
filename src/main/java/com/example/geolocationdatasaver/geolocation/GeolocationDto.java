package com.example.geolocationdatasaver.geolocation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeolocationDto {

    @NotBlank
    private String deviceId;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Date updatedAt;

    public GeolocationDto(String deviceId, Double latitude, Double longitude) {
        this.deviceId = deviceId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
