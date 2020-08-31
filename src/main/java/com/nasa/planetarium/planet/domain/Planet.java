package com.nasa.planetarium.planet.domain;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

    @Id
    private Integer id;
    private String name;
    private Float gravity;
    private Integer satellites;
    private Float maxTemperature;
    private Float minTemperature;
    private Float meanTemperature;
}