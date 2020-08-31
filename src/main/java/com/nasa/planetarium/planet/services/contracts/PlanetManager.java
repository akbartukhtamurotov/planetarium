package com.nasa.planetarium.planet.services.contracts;

import com.nasa.planetarium.planet.domain.Planet;

import reactor.core.publisher.Mono;

public interface PlanetManager {
    
    /**
     * Save planet in database. Takes already filled planet.
     * 
     * @param planet
     * @return
     */
    public Mono<Planet> savePlanet(Planet planet);

    /**
     * Retrieve planet with given ID
     * 
     * @param id
     * @return
     */
    public Mono<Planet> getPlanet(Integer id);
}