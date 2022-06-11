package com.diegoosorio.birds.birds.aplication.services;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.BirdId;
import com.diegoosorio.birds.birds.aplication.ports.input.QueryBirdByIdUseCase;
import com.diegoosorio.birds.birds.aplication.ports.output.BirdsRepository;
import com.diegoosorio.birds.infrastructure.models.birds.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueryBirdByIdService implements QueryBirdByIdUseCase {
    private final BirdsRepository birdsRepository;

    public QueryBirdByIdService(BirdsRepository birdsRepository) {
        this.birdsRepository = birdsRepository;
    }

    @Override
    public Optional<BirdDTO> execute(Long birdId) {
        BirdId birdIdInput = new BirdId(birdId);
        Optional<Bird> birdOptional = birdsRepository.get(birdIdInput);
        if(birdOptional.isPresent()){
            return birdOptional.map(bird -> {
                BirdDTO birdDTO = BirdDTO.fromToDomain(birdOptional.get());
                birdDTO.setStatus("Ok");
                birdDTO.setMessage("Bird found");
                return birdDTO;
            });
        }
        return Optional.empty();

    }
}
