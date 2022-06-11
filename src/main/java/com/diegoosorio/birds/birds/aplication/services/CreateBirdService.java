package com.diegoosorio.birds.birds.aplication.services;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.*;
import com.diegoosorio.birds.birds.aplication.ports.input.CreateBirdUseCase;
import com.diegoosorio.birds.birds.aplication.ports.output.BirdsRepository;
import com.diegoosorio.birds.infrastructure.models.birds.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateBirdService implements CreateBirdUseCase {
    private final BirdsRepository birdsRepository;

    public CreateBirdService(BirdsRepository birdsRepository) {
        this.birdsRepository = birdsRepository;
    }

    public BirdDTO execute(BirdDTO birdDTO){
        Bird bird = birdDTO.toUnsavedDomain(1);
        Optional<Bird> birdValidUnique = birdsRepository.getUniqueBird(bird);
        if(birdValidUnique.isEmpty()) {
            Bird birdSaved = birdDTO.toUnsavedDomain(birdDTO.getConfirmedQuantity());
            birdsRepository.store(birdSaved);
            birdDTO.setStatus("Saved");
            birdDTO.setMessage("The named "+ birdDTO.getCommonName() +" bird has been saved correctly.");
        } else {
            birdDTO.setStatus("UnSaved");
            birdDTO.setMessage("The bird "+ birdDTO.getCommonName() +" already exists in the database.");
        }
        return birdDTO;
    }
}
