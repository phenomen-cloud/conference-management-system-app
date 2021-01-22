package com.vironit.conferencemanagmentsystem.service;

import com.vironit.conferencemanagmentsystem.enums.Gender;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.RegistrationRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RegistrationService {
    List<Conference> findAvailableConferences(LocalDateTime startDateTime, LocalDateTime finishDateTime);
    Conference selectConference(Long id);
    RegistrationRequest registerForConference(Long conferenceId, String firstName, String secondName, LocalDate birthDate, Gender gender, String email);
    void cancelRegistration(String registrationCode);
}
