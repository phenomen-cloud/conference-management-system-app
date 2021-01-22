package com.vironit.conferencemanagmentsystem.repository;

import com.vironit.conferencemanagmentsystem.model.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<ConferenceRoom, Long> {
}
