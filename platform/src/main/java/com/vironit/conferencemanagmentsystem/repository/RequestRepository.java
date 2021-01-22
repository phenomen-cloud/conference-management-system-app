package com.vironit.conferencemanagmentsystem.repository;

import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RegistrationRequest, Long> {
    List<RegistrationRequest> findAllByConference(Conference conference);
    void deleteAllByConference(Conference conference);
    Optional<RegistrationRequest> findByParticipantCode(String participantCode);
}
