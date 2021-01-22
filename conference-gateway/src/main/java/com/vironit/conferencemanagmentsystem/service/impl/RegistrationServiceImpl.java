package com.vironit.conferencemanagmentsystem.service.impl;

import com.vironit.conferencemanagmentsystem.enums.Gender;
import com.vironit.conferencemanagmentsystem.exception.ConferenceNotFoundException;
import com.vironit.conferencemanagmentsystem.exception.RegistrationRequestNotFoundException;
import com.vironit.conferencemanagmentsystem.exception.RegistrationServiceException;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.RegistrationRequest;
import com.vironit.conferencemanagmentsystem.repository.ConferenceRepository;
import com.vironit.conferencemanagmentsystem.repository.RequestRepository;
import com.vironit.conferencemanagmentsystem.service.RegistrationLogic;
import com.vironit.conferencemanagmentsystem.service.RegistrationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@Transactional(rollbackOn = Exception.class)
public class RegistrationServiceImpl implements RegistrationService {

    private ConferenceRepository conferenceRepository;
    private RequestRepository requestRepository;
    private RegistrationLogic registrationLogic;

    @Autowired
    public void setConferenceRepository(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Autowired
    public void setRegistrationLogic(RegistrationLogic registrationLogic) {
        this.registrationLogic = registrationLogic;
    }

    @Override
    public Conference selectConference(Long id) throws ConferenceNotFoundException {
        log.info("Select conference by id: {}", id);
        return conferenceRepository.findById(id).orElseThrow(() -> new ConferenceNotFoundException("Conference not found by id " + id));
    }

    @Override
    public List<Conference> findAvailableConferences(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        log.info("Find available conferences between {} and {}", startDateTime, finishDateTime);
        return conferenceRepository.findAllByDateTimeBetween(startDateTime, finishDateTime);
    }

    @Override
    public RegistrationRequest registerForConference(Long conferenceId, String firstName, String secondName, LocalDate birthDate, Gender gender, String email) {
        log.info("Register for selected conference with id: {}", conferenceId);
        try {
            Conference conference = this.selectConference(conferenceId);
            RegistrationRequest registrationRequest = new RegistrationRequest(birthDate, gender, firstName, secondName, email, conference);
            registrationRequest.setParticipantCode(registrationLogic.getUniqueParticipantCode());
            requestRepository.saveAndFlush(registrationRequest);
            return registrationRequest;
        } catch (ConferenceNotFoundException exception) {
            log.error("Cannot register to selected conference");
            throw new RegistrationServiceException("Cannot register to selected conference with id " + conferenceId, exception);
        }
    }

    @Override
    public void cancelRegistration(String registrationCode) throws RegistrationRequestNotFoundException {
        log.info("Cancel selected registration with code: {}", registrationCode);
        try {
            RegistrationRequest registrationRequest = requestRepository.findByParticipantCode(registrationCode).orElseThrow(
                    () -> new RegistrationRequestNotFoundException("Registration not found by code " + registrationCode));
            requestRepository.delete(registrationRequest);
        } catch (RegistrationRequestNotFoundException exception) {
            log.error("Cancel registration failed");
            throw new RegistrationServiceException("Cannot cancel registration with code" + registrationCode, exception);
        }
    }
}
