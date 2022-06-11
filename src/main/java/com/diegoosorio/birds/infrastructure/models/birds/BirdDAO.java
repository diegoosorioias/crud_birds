package com.diegoosorio.birds.infrastructure.models.birds;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdDAO {
    private Long id;
    private String commonName;
    private String scientificName;
    private String nameZone;
    private Integer confirmedQuantity;

    public BirdDAO(Long id, String commonName, String scientificName, String nameZone, Integer confirmedQuantity) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.nameZone = nameZone;
        this.confirmedQuantity = confirmedQuantity;
    }

    public BirdDAO() {
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

    public static BirdDAO fromToDomain(Bird bird){
        BirdDAO birdDAO = new BirdDAO(
          bird.getId().getValue(),
          bird.getCommonName().getValue(),
          bird.getScientificName().getValue(),
          bird.getNameZone().getValue(),
          bird.getConfirmedQuantity().getValue()
        );
        return birdDAO;
    }

    public static BirdDAO fromResulSet(ResultSet resultSet) throws SQLException {
        BirdDAO birdDAO = new BirdDAO();
        birdDAO.setId(resultSet.getLong("id"));
        birdDAO.setCommonName(resultSet.getString("common_name"));
        birdDAO.setScientificName(resultSet.getString("scientific_name"));
        birdDAO.setNameZone(resultSet.getString("name_zone"));
        birdDAO.setConfirmedQuantity(resultSet.getInt("confirmed_quantity"));
        return birdDAO;
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

    @Override
    public String toString() {
        return "BirdDAO{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", nameZone='" + nameZone + '\'' +
                ", confirmedQuantity=" + confirmedQuantity +
                '}';
    }
}
