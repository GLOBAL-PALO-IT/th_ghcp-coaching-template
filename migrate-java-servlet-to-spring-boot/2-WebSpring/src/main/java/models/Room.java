package models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private long roomID;
    private String roomName;
    private Building building;

    public Room() {
        super();
    }

    public Room(String roomName, Building building) {
        super();
        this.roomName = roomName;
        this.building = building;
    }

    @Id
    @GeneratedValue
    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @OneToOne(cascade = { CascadeType.ALL })
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}