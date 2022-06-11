package com.diegoosorio.birds.birds.aplication.services;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.BirdId;
import com.diegoosorio.birds.birds.aplication.ports.input.UpdateBirdUseCase;
import com.diegoosorio.birds.birds.aplication.ports.output.BirdsRepository;
import com.diegoosorio.birds.infrastructure.models.birds.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBirdService implements UpdateBirdUseCase {
    private final BirdsRepository birdsRepository;

    public UpdateBirdService(BirdsRepository birdsRepository) {
        this.birdsRepository = birdsRepository;
    }

    @Override
    public BirdDTO execute(BirdDTO birdDTO) {
        Bird bird = birdDTO.toDomain();
        Optional<Bird> birdValidUnique = birdsRepository.getUniqueBird(bird);
        Optional<Bird> birValidExist = birdsRepository.get(new BirdId(birdDTO.getId()));
        if(birValidExist.isEmpty()){
            birdDTO.setStatus("UnSaved");
            birdDTO.setMessage("The bird with id "+ birdDTO.getId() +" does not exist in the database.");
        }
        else if(birdValidUnique.isEmpty() || (birdValidUnique.isPresent() &&  bird.getId().getValue().toString().equals(birdValidUnique.get().getId().toString()))) {
            Bird birdSaved = birdDTO.toDomain();
            birdsRepository.update(birdSaved);
            birdDTO.setStatus("Saved");
            birdDTO.setMessage("The named "+ birdDTO.getCommonName() +" bird has been update correctly.");
        } else {
            birdDTO.setStatus("UnSaved");
            birdDTO.setMessage("The bird "+ birdDTO.getCommonName() +" already exists in the database.");
        }
        return birdDTO;
    }
}
