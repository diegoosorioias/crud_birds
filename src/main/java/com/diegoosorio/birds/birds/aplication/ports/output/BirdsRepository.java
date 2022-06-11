package com.diegoosorio.birds.birds.aplication.ports.output;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.BirdId;

import java.util.Optional;

public interface BirdsRepository {
    void store(Bird bird);

    Optional<Bird> get(BirdId birdId);

    Optional<Bird> getUniqueBird(Bird bird);

    void update(Bird bird);

    Boolean delete(BirdId birdId);
}
