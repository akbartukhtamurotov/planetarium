package com.nasa.planetarium.web.controllers;

import java.util.HashMap;
import java.util.Map;

import com.nasa.planetarium.planet.domain.Planet;
import com.nasa.planetarium.planet.exceptions.PlanetNotFoundException;
import com.nasa.planetarium.planet.payloads.PlanetPayload;
import com.nasa.planetarium.planet.services.contracts.PlanetManager;
import com.nasa.planetarium.planet.services.contracts.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final PlanetService planetService;
    private final PlanetManager planetManager;

    @Autowired
    public PlanetController(PlanetService planetService, PlanetManager planetManager) {
        this.planetService = planetService;
        this.planetManager = planetManager;
    }

    @GetMapping("/{id}")
    public Mono<Planet> getOne(@PathVariable(name = "id") Integer id) {
        return this.planetManager.getPlanet(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Planet> store(@RequestBody PlanetPayload payload) {
        return this.planetService.createNewPlanet(payload);
    }

    @PutMapping("/{id}")
    public Mono<Planet> update(@RequestBody PlanetPayload planetPayload, @PathVariable(name = "id") Integer id) {
        return this.planetService.updatePlanet(id, planetPayload);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlanetNotFoundException.class)
    public Mono<Map<String, String>> planetNotFoundHandler(PlanetNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());
        return Mono.just(error);
    }
}