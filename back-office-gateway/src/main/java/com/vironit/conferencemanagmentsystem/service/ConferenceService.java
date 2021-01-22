package com.vironit.conferencemanagmentsystem.service;

import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.Feedback;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceService {
    Conference createConference(String name, LocalDateTime dateTime, Long roomId);
    Conference selectConference(Long id);
    void cancelConference(Long id);
    Boolean isConferenceAvailable(Long id) ;
    void updateConferenceRoom(Long conferenceId, Long roomId);
    void updateConferenceDateTime(Long id, LocalDateTime dateTime);
    List<Feedback> findAllFeedbacks(Long id);
}
