package com.nasa.planetarium.planet.services.contracts;

import com.nasa.planetarium.planet.domain.Planet;
import com.nasa.planetarium.planet.payloads.PlanetPayload;

import reactor.core.publisher.Mono;

public interface PlanetService {
    
    /**
     * Create a new planet from PlanetPayload
     * 
     * @param planetPayload
     * @return
     */
    public Mono<Planet> createNewPlanet(PlanetPayload planetPayload);

    /**
     * Update existing planet with given ID
     * 
     * @param id
     * @param planetPayload
     * @return
     */
    public Mono<Planet> updatePlanet(Integer id, PlanetPayload planetPayload); 
}