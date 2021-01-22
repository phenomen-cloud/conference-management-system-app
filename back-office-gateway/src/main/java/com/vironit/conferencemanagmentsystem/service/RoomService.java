package com.vironit.conferencemanagmentsystem.service;

import com.vironit.conferencemanagmentsystem.enums.RoomStatus;
import com.vironit.conferencemanagmentsystem.model.ConferenceRoom;

public interface RoomService {
    ConferenceRoom createRoom(String name, String location, int capacity);
    ConferenceRoom selectRoom(Long id);
    void updateCapacity(Long id, int capacity);
    void updateStatus(Long id, RoomStatus status);
}
