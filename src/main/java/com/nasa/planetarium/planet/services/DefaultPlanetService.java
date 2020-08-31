package com.nasa.planetarium.planet.services;

import com.nasa.planetarium.planet.domain.Planet;
import com.nasa.planetarium.planet.payloads.PlanetPayload;
import com.nasa.planetarium.planet.services.contracts.PlanetManager;
import com.nasa.planetarium.planet.services.contracts.PlanetService;
import com.nasa.planetarium.planet.utils.PlanetUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DefaultPlanetService implements PlanetService {

    private final PlanetManager planetManager;

    @Autowired
    public DefaultPlanetService(PlanetManager planetManager) {
        this.planetManager = planetManager;
    }

    public Mono<Planet> createNewPlanet(PlanetPayload planetPayload) {
        return planetManager.savePlanet(buildPlanet(planetPayload));
    }

    public Mono<Planet> updatePlanet(Integer id, PlanetPayload planetPayload) {
        return planetManager.getPlanet(id)
                .map(planet -> {
                    return transformPlanet(planetPayload, planet);
                })
                .flatMap(planetManager::savePlanet);
    }

    private Planet buildPlanet(PlanetPayload planetPayload) {
        Float maxTemperature = planetPayload.getMaxTemperature();
        Float minTemperature = planetPayload.getMinTemperature();

        return Planet.builder()
                .name(planetPayload.getName())
                .gravity(planetPayload.getGravity())
                .satellites(planetPayload.getSatellites())
                .maxTemperature(maxTemperature).minTemperature(minTemperature)
                .meanTemperature(PlanetUtils.calculateMeanTemperature(maxTemperature, minTemperature).orElse(null))
                .build();
    }

    private Planet transformPlanet(PlanetPayload planetPayload, Planet planet) {
        planet.setName(planetPayload.getName());
        planet.setGravity(planetPayload.getGravity());
        planet.setSatellites(planetPayload.getSatellites());
        planet.setMaxTemperature(planetPayload.getMaxTemperature());
        planet.setMinTemperature(planetPayload.getMinTemperature());
        planet.setMeanTemperature(PlanetUtils
                .calculateMeanTemperature(planet.getMaxTemperature(), planet.getMinTemperature()).orElse(null));

        return planet;
    }
}