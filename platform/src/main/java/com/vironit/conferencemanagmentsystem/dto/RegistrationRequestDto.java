package com.vironit.conferencemanagmentsystem.dto;

import com.vironit.conferencemanagmentsystem.dto.transfer.CancelRegistration;
import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import com.vironit.conferencemanagmentsystem.enums.Gender;
import com.vironit.conferencemanagmentsystem.model.Conference;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Data
public class RegistrationRequestDto {
    @Null(groups = {New.class})
    private Long id;
    @NotNull(groups = {New.class})
    private LocalDate birthDate;
    @NotNull(groups = {New.class})
    private Gender gender;
    @NotNull(groups = {New.class})
    private String firstName;
    @NotNull(groups = {New.class})
    private String secondName;
    @NotNull(groups = {New.class})
    private String email;
    @Null(groups = {New.class})
    @NotNull(groups = {CancelRegistration.class})
    private String participantCode;
    @NotNull(groups = {New.class})
    private ConferenceDto conference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }

    public ConferenceDto getConference() {
        return conference;
    }

    public void setConference(ConferenceDto conference) {
        this.conference = conference;
    }
}
