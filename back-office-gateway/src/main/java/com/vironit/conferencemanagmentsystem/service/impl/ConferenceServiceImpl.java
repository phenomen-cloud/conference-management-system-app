package com.vironit.conferencemanagmentsystem.service.impl;

import com.vironit.conferencemanagmentsystem.enums.ConferenceStatus;
import com.vironit.conferencemanagmentsystem.enums.RoomStatus;
import com.vironit.conferencemanagmentsystem.exception.ConferenceNotFoundException;
import com.vironit.conferencemanagmentsystem.exception.ConferenceServiceException;
import com.vironit.conferencemanagmentsystem.exception.RoomNotFoundException;
import com.vironit.conferencemanagmentsystem.exception.RoomServiceException;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.ConferenceRoom;
import com.vironit.conferencemanagmentsystem.model.Feedback;
import com.vironit.conferencemanagmentsystem.repository.ConferenceRepository;
import com.vironit.conferencemanagmentsystem.repository.FeedbackRepository;
import com.vironit.conferencemanagmentsystem.repository.RequestRepository;
import com.vironit.conferencemanagmentsystem.service.ConferenceService;
import com.vironit.conferencemanagmentsystem.service.RoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@Transactional(rollbackOn = Exception.class)
public class ConferenceServiceImpl implements ConferenceService {
    private ConferenceRepository conferenceRepository;
    private RequestRepository requestRepository;
    private FeedbackRepository feedbackRepository;
    private RoomService roomService;

    @Autowired
    public void setConferenceRepository(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setFeedbackRepository(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Conference createConference(String name, LocalDateTime dateTime, Long roomId) throws ConferenceServiceException {
        log.info("Create conference with name: {}, and dateTime: {}", name, dateTime);
        try {
            ConferenceRoom conferenceRoom = roomService.selectRoom(roomId);
            if (conferenceRoom.getStatus() == RoomStatus.UNDER_CONSTRUCTION) {
                throw new RoomServiceException("Room with id" + roomId + "is unable to be occupied, its under construction");
            } else {
                List<Conference> conferences = conferenceRepository.findAllByDateTimeAndConferenceRoom(dateTime, conferenceRoom);
                if (!conferences.isEmpty()) {
                    throw new ConferenceServiceException("There is another conference in this room in this dateTime, choose another one");
                }
                return conferenceRepository.saveAndFlush(new Conference(name, dateTime));
            }
        } catch (RoomServiceException exception) {
            log.error("Cannot create conference in room with id {}, exception {}", roomId, exception);
            throw new ConferenceServiceException("Cannot create conference", exception);
        } catch (ConferenceServiceException exception) {
            log.error("Cannot create conference in room with id {}, exception {} in dateTime {}", roomId, exception, dateTime);
            throw new ConferenceServiceException("Cannot create conference", exception);
        }
    }

    @Override
    public Conference selectConference(Long id) throws ConferenceNotFoundException {
        log.info("Select conference with id: {}", id);
        return conferenceRepository.findById(id).orElseThrow(() -> new ConferenceNotFoundException("Conference not found by id " + id));
    }

    @Override
    public void cancelConference(Long id) throws ConferenceServiceException {
        log.info("Cancel conference with id: {}", id);
        try {
            Conference conference = this.selectConference(id);
            conference.setStatus(ConferenceStatus.CANCELED);
            requestRepository.deleteAllByConference(conference);
        } catch (ConferenceNotFoundException exception) {
            log.error("Cannot update conference with id: {}, exception: {}", id, exception);
            throw new ConferenceServiceException("Cannot cancel conference with id " + id, exception);
        }
    }

    @Override
    public Boolean isConferenceAvailable(Long id) throws ConferenceServiceException {
        log.info("Check is conference with id {} available", id);
        try {
            Conference conference = this.selectConference(id);
            return conference.getConferenceRoom().getCapacity() > conference.getRegistrationRequests().size();
        } catch (ConferenceNotFoundException exception) {
            log.error("Cannot update conference with id: {}, exception: {}", id, exception);
            throw new ConferenceServiceException("Cannot cancel conference with id " + id, exception);
        }
    }

    @Override
    public void updateConferenceRoom(Long conferenceId, Long roomId) throws ConferenceServiceException {
        log.info("Update conference with id: {} with new room {}", conferenceId, roomId);
        try {
            Conference conference = this.selectConference(conferenceId);
            ConferenceRoom conferenceRoom = roomService.selectRoom(roomId);
            List<Conference> conferences = conferenceRepository.findAllByConferenceRoomAndStatus(conferenceRoom, ConferenceStatus.COMING_SOON);
            if (conferenceRoom.getStatus() != RoomStatus.UNDER_CONSTRUCTION && !conferences.isEmpty()) {
                conference.setConferenceRoom(conferenceRoom);
                conferenceRepository.saveAndFlush(conference);
            } else throw new ConferenceServiceException("Room is under construction or there are any conferences in this room in future");
        } catch (ConferenceNotFoundException exception) {
            log.error("Cannot update conference with id: {}, exception: {}", conferenceId, exception);
            throw new ConferenceServiceException("Cannot found conference with id " + conferenceId, exception);
        } catch (RoomNotFoundException exception) {
            log.error("Cannot update conference with id: {} by room with id: {}, exception: {}", conferenceId, roomId, exception);
            throw new RoomServiceException("Cannot found room with id " + roomId, exception);
        } catch (Exception exception) {
            log.error("Cannot update conference with id {}, exception: {}", conferenceId, exception);
            throw new ConferenceServiceException(exception);
        }
    }

    @Override
    public void updateConferenceDateTime(Long id, LocalDateTime dateTime) throws ConferenceServiceException {
        log.info("Update conference with id: {} with new dateTime {}", id, dateTime);
        try {
            Conference conference = this.selectConference(id);
            conference.setDateTime(dateTime);
            conferenceRepository.saveAndFlush(conference);
        } catch (ConferenceNotFoundException exception) {
            log.error("Cannot update conference with id: {}, exception: {}", id, exception);
            throw new ConferenceServiceException("Cannot found conference with id " + id, exception);
        } catch (Exception exception) {
            log.error("Cannot update conference with id: {}, exception: {}", id, exception);
            throw new ConferenceServiceException(exception);
        }
    }

    @Override
    public List<Feedback> findAllFeedbacks(Long id) throws ConferenceServiceException {
        log.info("Find all feedbacks for conference with id: {}", id);
        try {
            Conference conference = this.selectConference(id);
            return feedbackRepository.findAllByConference(conference);
        } catch (ConferenceNotFoundException exception) {
            log.error("Cannot find feedbacks of conference with id: {}, exception: {}", id, exception);
            throw new ConferenceServiceException("Cannot found conference with id " + id, exception);
        } catch (Exception exception) {
            log.error("Cannot find feedbacks of conference with id: {}, exception: {}", id, exception);
            throw new ConferenceServiceException(exception);
        }
    }
}
