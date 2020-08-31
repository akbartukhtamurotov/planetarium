package com.nasa.planetarium.unit.services;

import com.nasa.planetarium.planet.domain.Planet;
import com.nasa.planetarium.planet.payloads.PlanetPayload;
import com.nasa.planetarium.planet.services.DefaultPlanetService;
import com.nasa.planetarium.planet.services.contracts.PlanetManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class DefaultPlanetServiceTest {

    @Mock
    private PlanetManager planetManager;

    @InjectMocks
    private DefaultPlanetService planetService;

    @Test
    void testCreateNewPlanet() {
        Mockito.when(planetManager.savePlanet(predefinedPlanetWithoutId())).thenReturn(Mono.just(predefinedPlanetWithId()));
        PlanetPayload planetPayload = PlanetPayload.builder()
                .name("Mustafar")
                .gravity(18.08f)
                .maxTemperature(120f).minTemperature(30f)
                .satellites(15).build();
        Mono<Planet> planetPublisher = planetService.createNewPlanet(planetPayload);
        StepVerifier.create(planetPublisher).expectNext(predefinedPlanetWithId()).expectComplete();
    }

    @Test
    void testUpdatePlanet() {
        Mockito.when(planetManager.getPlanet(17)).thenReturn(Mono.just(predefinedPlanetWithId()));

        PlanetPayload planetPayload = PlanetPayload.builder()
                .name("Dagobah")
                .gravity(3.71f)
                .maxTemperature(60f).minTemperature(30f)
                .satellites(3).build();

        StepVerifier.create(planetService.updatePlanet(17, planetPayload)).expectNext(predefinedPlanetToUpdate()).expectComplete();
    }

    private Planet predefinedPlanetWithoutId() {
        return Planet.builder().name("Mustafar").gravity(18.08f).maxTemperature(120f).minTemperature(30f)
                .meanTemperature(75f).satellites(15).build();
    }

    private Planet predefinedPlanetWithId() {
        return Planet.builder().id(17).name("Mustafar").gravity(18.08f).maxTemperature(120f).minTemperature(30f)
                .meanTemperature(75f).satellites(15).build();
    }

    private Planet predefinedPlanetToUpdate() {
        return Planet.builder().id(17).name("Dagobah").gravity(3.71f).maxTemperature(60f).minTemperature(30f)
                .meanTemperature(45f).satellites(3).build();
    }
}