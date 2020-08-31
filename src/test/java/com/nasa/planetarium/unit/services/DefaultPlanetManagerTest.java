package com.nasa.planetarium.unit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nasa.planetarium.planet.data.PlanetRepository;
import com.nasa.planetarium.planet.domain.Planet;
import com.nasa.planetarium.planet.services.DefaultPlanetManager;
import com.nasa.planetarium.planet.utils.PlanetUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class DefaultPlanetManagerTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private DefaultPlanetManager planetManager;

    @Test
    void testGetPlanetSuccess() {
        Mockito.when(planetRepository.findById(19)).thenReturn(Mono.just(fakePlanet()));
        planetManager.getPlanet(19).subscribe(planet -> {
            assertEquals(planet, fakePlanet());
        });
    }

    private Planet fakePlanet() {
        float max = -20f;
        float min = -200f;
        return Planet.builder().id(19).name("Hoth").gravity(8.0f).maxTemperature(-20f).minTemperature(-200f)
                .meanTemperature(PlanetUtils.calculateMeanTemperature(max, min).orElse(null)).satellites(23).build();
    }

}