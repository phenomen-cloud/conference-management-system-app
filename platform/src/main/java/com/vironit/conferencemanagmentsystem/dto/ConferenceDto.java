package com.vironit.conferencemanagmentsystem.dto;

import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateDateTime;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateStatus;
import com.vironit.conferencemanagmentsystem.enums.ConferenceStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class ConferenceDto {
    @Null(groups = {New.class})
    private Long id;
    @NotNull(groups = {New.class})
    private String name;
    @NotNull(groups = {New.class, UpdateStatus.class})
    private ConferenceStatus status;
    @NotNull(groups = {New.class, UpdateDateTime.class})
    private LocalDateTime dateTime;
    @NotNull(groups = {New.class})
    private ConferenceRoomDto conferenceRoom;

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

    public ConferenceStatus getStatus() {
        return status;
    }

    public void setStatus(ConferenceStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ConferenceRoomDto getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoomDto conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }
}
