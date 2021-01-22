package com.vironit.conferencemanagmentsystem.dto;

import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateCapacity;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateRoom;
import com.vironit.conferencemanagmentsystem.enums.RoomStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class ConferenceRoomDto {
    @Null(groups = {New.class})
    @NotNull(groups = {UpdateRoom.class})
    private Long id;
    @NotNull(groups = {New.class})
    private String name;
    @NotNull(groups = {New.class})
    private RoomStatus status;
    @NotNull(groups = {New.class})
    private String location;
    @NotNull(groups = {New.class, UpdateCapacity.class})
    private int capacity;
    private ConferenceDto conference;

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

    public ConferenceDto getConference() {
        return conference;
    }

    public void setConference(ConferenceDto conference) {
        this.conference = conference;
    }
}
