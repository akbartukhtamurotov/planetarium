package com.nasa.planetarium.planet.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.util.annotation.NonNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetPayload {

    @NonNull
    private String name;
    private Float gravity;
    private Integer satellites;
    private Float maxTemperature;
    private Float minTemperature;
}