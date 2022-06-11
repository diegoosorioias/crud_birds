package com.diegoosorio.birds.birds.aplication.services;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.ports.input.QueryBirdUniqueUseCase;
import com.diegoosorio.birds.birds.aplication.ports.output.BirdsRepository;
import com.diegoosorio.birds.infrastructure.models.birds.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueryBirdUniqueUseCaseService implements QueryBirdUniqueUseCase {
    private final BirdsRepository birdsRepository;

    public QueryBirdUniqueUseCaseService(BirdsRepository birdsRepository) {
        this.birdsRepository = birdsRepository;
    }

    @Override
    public Optional<BirdDTO> execute(Bird bird) {
        Optional<Bird> birdOptional = birdsRepository.getUniqueBird(bird);
        if(!birdOptional.isPresent()) return Optional.of(null);
        return birdOptional.map(product -> {
            BirdDTO birdDTOOutput = BirdDTO.fromToDomain(product);
            return birdDTOOutput;
        });
    }
}
