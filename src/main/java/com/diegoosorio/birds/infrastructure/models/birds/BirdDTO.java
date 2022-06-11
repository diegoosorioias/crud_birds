package com.diegoosorio.birds.infrastructure.models.birds;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.*;

import java.util.Objects;

public class BirdDTO {
    private Long id;
    private String commonName;
    private String scientificName;
    private String nameZone;
    private Integer confirmedQuantity;

    private String status;

    private String message;

    public BirdDTO(Long id, String commonName, String scientificName, String nameZone, Integer confirmedQuantity) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.nameZone = nameZone;
        this.confirmedQuantity = confirmedQuantity;
    }

    public BirdDTO() {

    }

    public Bird toDomain(){
        return new Bird(
                new BirdId(id),
                new BirdCommonName(commonName),
                new BirdScientificName(scientificName),
                new BirdNameZone(nameZone),
                new BirdConfirmedQuantity(confirmedQuantity)
        );
    }

    public Bird toUnsavedDomain(Integer Quantity){
        return new Bird(
                null,
                new BirdCommonName(commonName),
                new BirdScientificName(scientificName),
                new BirdNameZone(nameZone),
                new BirdConfirmedQuantity(Quantity)
        );
    }

    public static BirdDTO fromToDomain(Bird bird){
        BirdDTO birdDTO = new BirdDTO(
                bird.getId().getValue(),
                bird.getCommonName().getValue(),
                bird.getScientificName().getValue(),
                bird.getNameZone().getValue(),
                bird.getConfirmedQuantity().getValue()
        );
        return birdDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getNameZone() {
        return nameZone;
    }

    public void setNameZone(String nameZone) {
        this.nameZone = nameZone;
    }

    public Integer getConfirmedQuantity() {
        return confirmedQuantity;
    }

    public void setConfirmedQuantity(Integer confirmedQuantity) {
        this.confirmedQuantity = confirmedQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BirdDTO{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", nameZone='" + nameZone + '\'' +
                ", confirmedQuantity=" + confirmedQuantity +
                '}';
    }
}
