package com.nasa.planetarium.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nasa.planetarium.planet.utils.PlanetUtils;

import org.junit.jupiter.api.Test;

public class PlanetUtilsTest {
    
    @Test
    void testCalculateMeanTemperatureSuccess() {
        float max = 200;
        float min = 100;

        assertEquals(150, PlanetUtils.calculateMeanTemperature(max, min).get());
    }

    @Test
    void testCalculateMeanTemperatureMaxTemperatureNullEmptyResult() {
        Float max = null;
        Float min = Float.valueOf(200);

        assertTrue(PlanetUtils.calculateMeanTemperature(max, min).isEmpty());
    }

    @Test
    void testCalculateMeanTemperatureMinTemperatureNullEmptyResult() {
        Float max = Float.valueOf(200);
        Float min = null;

        assertTrue(PlanetUtils.calculateMeanTemperature(max, min).isEmpty());
    }

    @Test
    void testCalculateMeanTemperatureBothTemperaturesNullEmptyResult() {
        Float max = null;
        Float min = null;

        assertTrue(PlanetUtils.calculateMeanTemperature(max, min).isEmpty());
    }
}