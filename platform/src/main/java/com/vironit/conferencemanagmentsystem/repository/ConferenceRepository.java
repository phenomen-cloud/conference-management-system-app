package com.vironit.conferencemanagmentsystem.repository;

import com.vironit.conferencemanagmentsystem.enums.ConferenceStatus;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    List<Conference> findAllByConferenceRoomAndStatus(ConferenceRoom conferenceRoom, ConferenceStatus status);
    List<Conference> findAllByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Conference> findAllByDateTimeAndConferenceRoom(LocalDateTime dateTime, ConferenceRoom conferenceRoom);
}
