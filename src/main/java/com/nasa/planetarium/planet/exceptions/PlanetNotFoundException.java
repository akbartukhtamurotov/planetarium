package com.nasa.planetarium.planet.exceptions;

public class PlanetNotFoundException extends Exception {
    
    private static final long serialVersionUID = -1335993152571286028L;

    private Integer planetId;

    public PlanetNotFoundException(Integer id) {
        super("Planet with id " + id + " not found");
        planetId = id;
    }

    public Integer getPlanetId() {
        return planetId;
    }
}