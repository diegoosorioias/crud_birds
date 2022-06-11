package com.diegoosorio.birds.birds.aplication.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdId {
    private final Long value;

    public BirdId(Long value) {
        Validate.notNull(value,"The id field cannot be empty or null.");
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
