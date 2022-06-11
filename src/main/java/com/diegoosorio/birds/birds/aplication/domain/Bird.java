package com.diegoosorio.birds.birds.aplication.domain;

import com.diegoosorio.birds.birds.aplication.domain.valueObjs.*;

public class Bird {
    private final BirdId id;
    private final BirdCommonName commonName;
    private final BirdScientificName scientificName;
    private final BirdNameZone nameZone;
    private final BirdConfirmedQuantity confirmedQuantity;


    public Bird(BirdId id, BirdCommonName commonName, BirdScientificName scientificName, BirdNameZone nameZone, BirdConfirmedQuantity confirmedQuantity) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.nameZone = nameZone;
        this.confirmedQuantity = confirmedQuantity;
    }

    public BirdId getId() {
        return id;
    }

    public BirdCommonName getCommonName() {
        return commonName;
    }

    public BirdScientificName getScientificName() {
        return scientificName;
    }

    public BirdNameZone getNameZone() {
        return nameZone;
    }

    public BirdConfirmedQuantity getConfirmedQuantity() {
        return confirmedQuantity;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", commonName=" + commonName +
                ", scientificName=" + scientificName +
                ", nameZone=" + nameZone +
                ", confirmedQuantity=" + confirmedQuantity +
                '}';
    }
}
