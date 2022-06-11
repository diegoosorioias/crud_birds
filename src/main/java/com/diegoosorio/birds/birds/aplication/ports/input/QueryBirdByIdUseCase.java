package com.diegoosorio.birds.birds.aplication.ports.input;

import com.diegoosorio.birds.commons.UseCase;
import com.diegoosorio.birds.infrastructure.models.birds.BirdDTO;

import java.util.Optional;

public interface QueryBirdByIdUseCase extends UseCase<Long, Optional<BirdDTO>> {
}
