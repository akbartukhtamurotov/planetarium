package com.nasa.planetarium.planet.services;

import com.nasa.planetarium.planet.data.PlanetRepository;
import com.nasa.planetarium.planet.domain.Planet;
import com.nasa.planetarium.planet.exceptions.PlanetNotFoundException;
import com.nasa.planetarium.planet.services.contracts.PlanetManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DefaultPlanetManager implements PlanetManager {

    private final PlanetRepository planetRepository;

    @Autowired
    public DefaultPlanetManager(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Mono<Planet> savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public Mono<Planet> getPlanet(Integer id) {
        return planetRepository.findById(id).switchIfEmpty(Mono.error(new PlanetNotFoundException(id)));
    }
}