package com.example.geolocationdatasaver.geolocation;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Geolocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank()
    @Column(nullable = false, unique = true)
    private String deviceId;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;

    public Geolocation(String deviceId, double latitude, double longitude) {
        this.deviceId = deviceId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Geolocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
