package com.vironit.conferencemanagmentsystem.service.impl;

import com.vironit.conferencemanagmentsystem.enums.ConferenceStatus;
import com.vironit.conferencemanagmentsystem.enums.RoomStatus;
import com.vironit.conferencemanagmentsystem.exception.RoomNotFoundException;
import com.vironit.conferencemanagmentsystem.exception.RoomServiceException;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.ConferenceRoom;
import com.vironit.conferencemanagmentsystem.repository.RoomRepository;
import com.vironit.conferencemanagmentsystem.service.RoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional(rollbackOn = Exception.class)
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public ConferenceRoom createRoom(String name, String location, int capacity) {
        return roomRepository.saveAndFlush(new ConferenceRoom(name, location, capacity));
    }

    @Override
    public ConferenceRoom selectRoom(Long id) throws RoomNotFoundException {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Conference room not found by id " + id));
    }

    @Override
    public void updateCapacity(Long id, int capacity) throws RoomServiceException {
        log.info("Update capacity of room with id: {}", id);
        try {
            ConferenceRoom conferenceRoom = this.selectRoom(id);
            List<Conference> conferences = conferenceRoom.getConference();
            List<Integer> numberOfRegistrations = conferences.stream().map(conference -> conference.getRegistrationRequests().size()).collect(Collectors.toList());
            for (Integer number : numberOfRegistrations) {
                if (number > capacity) {
                    throw new RoomServiceException("There are more registration request in conference than room capacity");
                }
            }
            conferenceRoom.setCapacity(capacity);
            roomRepository.saveAndFlush(conferenceRoom);
        } catch (RoomNotFoundException exception) {
            log.error("Cannot select room with id: {}, exception: {}", id, exception);
            throw new RoomServiceException("Cannot find room with id " + id, exception);
        } catch (Exception exception) {
            log.error("Cannot select room with id: {}, exception: {}", id, exception);
            throw new RoomServiceException(exception);
        }
    }

    @Override
    public void updateStatus(Long id, RoomStatus status) throws RoomServiceException {
        log.info("Update status of room with id: {}", id);
        try {
            ConferenceRoom conferenceRoom = this.selectRoom(id);
            if (status == RoomStatus.UNDER_CONSTRUCTION ) {
                List<Conference> conferences = conferenceRoom.getConference();
                List<ConferenceStatus> conferenceStatuses = conferences.stream().map(Conference::getStatus).collect(Collectors.toList());
                for (ConferenceStatus conferenceStatus : conferenceStatuses) {
                    if (conferenceStatus == ConferenceStatus.COMING_SOON) {
                        throw new RoomServiceException("There are planned conferences in this room on the future");
                    }
                }
            }
            conferenceRoom.setStatus(status);
            roomRepository.saveAndFlush(conferenceRoom);
        } catch (RoomNotFoundException exception) {
            log.error("Cannot select room with id: {}, exception: {}", id, exception);
            throw new RoomServiceException("Cannot find room with id " + id, exception);
        } catch (Exception exception) {
            log.error("Cannot select room with id: {}, exception: {}", id, exception);
            throw new RoomServiceException(exception);
        }
    }
}
