package com.diegoosorio.birds.birds.aplication.services;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.BirdId;
import com.diegoosorio.birds.birds.aplication.ports.input.DeleteBirdUseCase;
import com.diegoosorio.birds.birds.aplication.ports.output.BirdsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteBirdService implements DeleteBirdUseCase {
    private final BirdsRepository birdsRepository;

    public DeleteBirdService(BirdsRepository birdsRepository) {
        this.birdsRepository = birdsRepository;
    }

    @Override
    public Boolean execute(Long birdId) {
        Optional<Bird> birValidExist = birdsRepository.get(new BirdId(birdId));
        if(birValidExist.isPresent()){
            return birdsRepository.delete(new BirdId(birdId));
        }
        return false;
    }
}
