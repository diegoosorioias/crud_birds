package com.diegoosorio.birds.commons;

public interface UseCase<Input,Output>{
    Output execute(Input input);
}
