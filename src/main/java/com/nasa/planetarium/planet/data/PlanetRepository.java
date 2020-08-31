package com.nasa.planetarium.planet.data;

import com.nasa.planetarium.planet.domain.Planet;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PlanetRepository extends ReactiveCrudRepository<Planet, Integer> {
    
}