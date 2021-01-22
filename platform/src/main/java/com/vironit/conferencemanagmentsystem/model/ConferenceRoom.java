package com.vironit.conferencemanagmentsystem.model;

import com.vironit.conferencemanagmentsystem.enums.RoomStatus;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class ConferenceRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private RoomStatus status;
    private String location;
    private int capacity;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Conference> conference;

    public ConferenceRoom() {
    }

    public ConferenceRoom(String name, String location, int capacity) {
        this.name = name;
        this.status = RoomStatus.FREE;
        this.location = location;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Conference> getConference() {
        return conference;
    }

    public void setConference(List<Conference> conference) {
        this.conference = conference;
    }


}
