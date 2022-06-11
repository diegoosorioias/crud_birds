package com.diegoosorio.birds.birds.aplication.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdCommonName {
    private final String value;

    public BirdCommonName(String value) {
        Validate.notNull(value,"The common name field cannot be empty or null.");
        Validate.isTrue(value.length() <= 30,"The maximum common name size is 30 characters.");
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
