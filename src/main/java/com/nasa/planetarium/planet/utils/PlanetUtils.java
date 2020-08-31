package com.nasa.planetarium.planet.utils;

import java.util.Optional;

public class PlanetUtils {

    public static Optional<Float> calculateMeanTemperature(Float max, Float min) {
        return min == null || max == null ? Optional.empty() : Optional.of((max + min) / 2);
    }
}