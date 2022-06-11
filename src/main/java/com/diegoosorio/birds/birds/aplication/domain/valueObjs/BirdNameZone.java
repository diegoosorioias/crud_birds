package com.diegoosorio.birds.birds.aplication.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdNameZone {
    private final String value;

    public BirdNameZone(String value) {
        Validate.notNull(value,"The common name zone field cannot be empty or null.");
        Validate.isTrue(value.length() <= 30,"The maximum name zone size is 20 characters.");
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
