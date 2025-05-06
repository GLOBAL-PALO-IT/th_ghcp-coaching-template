package models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "building")
public class Building implements Serializable {
    private static final long serialVersionUID = 1L;
    private long buildingID;
    private String buildingName;

    public Building() {
        super();
    }

    public Building(String buildingName) {
        super();
        this.buildingName = buildingName;
    }

    @Id
    @GeneratedValue
    public long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(long buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}