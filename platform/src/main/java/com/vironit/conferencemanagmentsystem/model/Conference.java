package com.vironit.conferencemanagmentsystem.model;

import com.vironit.conferencemanagmentsystem.enums.ConferenceStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Conference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ConferenceStatus status;
    private LocalDateTime dateTime;
    @OneToOne(cascade = CascadeType.ALL)
    private ConferenceRoom conferenceRoom;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RegistrationRequest> registrationRequests;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    public Conference() {
    }

    public Conference(String name, LocalDateTime dateTime) {
        this.name = name;
        this.status = ConferenceStatus.COMING_SOON;
        this.dateTime = dateTime;
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

    public ConferenceRoom getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoom conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }

    public List<RegistrationRequest> getRegistrationRequests() {
        return registrationRequests;
    }

    public void setRegistrationRequests(List<RegistrationRequest> registrationRequests) {
        this.registrationRequests = registrationRequests;
    }
}
