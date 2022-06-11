package com.diegoosorio.birds.birds.aplication.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdScientificName {
    private final String value;

    public BirdScientificName(String value) {
        Validate.notNull(value,"The common scientific name field cannot be empty or null.");
        Validate.isTrue(value.length() <= 30,"The maximum scientific name size is 30 characters.");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
