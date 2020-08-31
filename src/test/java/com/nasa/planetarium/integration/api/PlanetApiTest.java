package com.nasa.planetarium.integration.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nasa.planetarium.planet.domain.Planet;
import com.nasa.planetarium.planet.payloads.PlanetPayload;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Flux;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetApiTest {

    @Autowired
    private WebTestClient webTestClient;

    private final static String BASE_URL_PLANET = "/planets";

    @Test
    void testCreatePlanetSuccess() {
        PlanetPayload planetPayload = buildPlanet();
        webTestClient
                .post()
                .uri(BASE_URL_PLANET)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(planetPayload))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Planet.class)
                .value(planet -> {
                    assertEquals(planetPayload.getName(), planet.getName());
                    assertEquals(planetPayload.getGravity(), planet.getGravity());
                    assertEquals(planetPayload.getSatellites(), planet.getSatellites());
                    assertEquals(planetPayload.getMaxTemperature(), planet.getMaxTemperature());
                    assertEquals(planet.getMinTemperature(), planet.getMinTemperature());
                });
    }

    @Test
    void testGetPlanetWithIdSuccess() {
        createPlanet().subscribe(expected -> {
            Planet actual = (Planet) webTestClient
                .get()
                .uri(BASE_URL_PLANET + "/" + expected.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(Planet.class);
                assertEquals(expected, actual);
        });
    }

    @Test
    void testGetPlanetWithNonExistingID() {
        webTestClient
                .get()
                .uri(BASE_URL_PLANET + "/" + Integer.MAX_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void testUpdatePlanetSuccess() {
        PlanetPayload updatePlanetPayload = PlanetPayload.builder()
                .name("Alderaan")
                .gravity(9.81f)
                .satellites(0)
                .maxTemperature(20f).minTemperature(-10f)
                .build();

        createPlanet().subscribe(beforeStoredPlanet -> {
            Planet updated = (Planet) webTestClient
                .put()
                .uri(BASE_URL_PLANET + "/" + beforeStoredPlanet.getId())
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(updatePlanetPayload))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Planet.class);
            assertEquals(updatePlanetPayload.getName(), updated.getName());
            assertEquals(updatePlanetPayload.getGravity(), updated.getGravity());
            assertEquals(updatePlanetPayload.getMaxTemperature(), updated.getMaxTemperature());
            assertEquals(updatePlanetPayload.getMinTemperature(), updated.getMinTemperature());
            assertEquals(updatePlanetPayload.getSatellites(), updatePlanetPayload.getSatellites());
        });
    }

    @Test
    void testUpdatePlanetWithNonExistingID() {
        webTestClient
                .put()
                .uri(BASE_URL_PLANET + "/" + Integer.MAX_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(buildPlanet()))
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    private Flux<Planet> createPlanet() {
        return webTestClient
                .post()
                .uri(BASE_URL_PLANET)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(buildPlanet()))
                .exchange()
                .returnResult(Planet.class)
                .getResponseBody();
    }

    private PlanetPayload buildPlanet() {
        return PlanetPayload.builder()
                .name("Tatooine")
                .gravity(9.81f)
                .satellites(3)
                .maxTemperature(60f).minTemperature(20f)
                .build();
    }
}