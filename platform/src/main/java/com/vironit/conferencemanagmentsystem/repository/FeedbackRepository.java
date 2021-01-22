package com.vironit.conferencemanagmentsystem.repository;

import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByConference(Conference conference);
}
