package com.vironit.conferencemanagmentsystem.model;

import com.vironit.conferencemanagmentsystem.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class RegistrationRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate birthDate;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String firstName;
    private String secondName;
    private String email;
    private String participantCode;
    @OneToOne
    private Conference conference;

    public RegistrationRequest() {
    }

    public RegistrationRequest(LocalDate birthDate, Gender gender, String firstName, String secondName, String email, Conference conference) {
        this.birthDate = birthDate;
        this.gender = gender;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.conference = conference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String participantFirstName) {
        this.firstName = participantFirstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String participantSecondName) {
        this.secondName = participantSecondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String participantEmail) {
        this.email = participantEmail;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate participantBirthDate) {
        this.birthDate = participantBirthDate;
    }

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender participantGender) {
        this.gender = participantGender;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
