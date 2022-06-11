package com.diegoosorio.birds.birds.aplication.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdConfirmedQuantity {
    private final Integer value;

    public BirdConfirmedQuantity(Integer value) {
        Validate.isTrue(value >=1 && value <= 100000,"The value confirmed quantity must be between 1 and 100000");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
