package com.vironit.conferencemanagmentsystem.controller;

import com.vironit.conferencemanagmentsystem.dto.ConferenceDto;
import com.vironit.conferencemanagmentsystem.dto.ConferenceRoomDto;
import com.vironit.conferencemanagmentsystem.dto.FeedbackDto;
import com.vironit.conferencemanagmentsystem.dto.converter.impl.ConferenceConverter;
import com.vironit.conferencemanagmentsystem.dto.converter.impl.FeedbackConverter;
import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateDateTime;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateRoom;
import com.vironit.conferencemanagmentsystem.exception.ConferenceNotFoundException;
import com.vironit.conferencemanagmentsystem.exception.ConferenceServiceException;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.Feedback;
import com.vironit.conferencemanagmentsystem.service.ConferenceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conference")
@Api(value = "Conference controller", description = "enable operations with conferences inside Back-Office-gateway")
public class ConferenceController {
    private ConferenceService conferenceService;
    private ConferenceConverter conferenceConverter;
    private FeedbackConverter feedbackConverter;

    @Autowired
    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Autowired
    public void setConferenceConverter(ConferenceConverter conferenceConverter) {
        this.conferenceConverter = conferenceConverter;
    }

    @Autowired
    public void setFeedbackConverter(FeedbackConverter feedbackConverter) {
        this.feedbackConverter = feedbackConverter;
    }

    @PostMapping(path = "/{roomId}/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDto> createConference(@PathVariable Long roomId,
                                                          @RequestBody  @Validated(New.class) ConferenceDto conferenceDto) {
        try {
            Conference conference = conferenceService.createConference(conferenceDto.getName(), conferenceDto.getDateTime(), roomId);
            return new ResponseEntity<>(conferenceConverter.entityToDto(conference), HttpStatus.OK);
        } catch (ConferenceServiceException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}/available")
    public ResponseEntity<Boolean> isConferenceAvailable(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(conferenceService.isConferenceAvailable(id), HttpStatus.OK);
        } catch (ConferenceNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}/cancel")
    public ResponseEntity<ConferenceDto> cancelConference(@PathVariable Long id) {
        try {
            conferenceService.cancelConference(id);
        } catch (ConferenceNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/update/room",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDto> updateRoom(@PathVariable Long id,
                                                    @RequestBody @Validated(UpdateRoom.class) ConferenceRoomDto conferenceRoomDto) {
        try {
            conferenceService.updateConferenceRoom(id, conferenceRoomDto.getId());
        } catch (ConferenceNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/update/dateTime",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDto> updateDateTime(@PathVariable Long id,
                                                        @RequestBody @Validated(UpdateDateTime.class) ConferenceDto conferenceDto) {
        try {
            conferenceService.updateConferenceDateTime(id, conferenceDto.getDateTime());
        } catch (ConferenceNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/feedback",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedbackDto>> findAllFeedbacks(@PathVariable Long id) {
        List<Feedback> feedbacks;
        try {
            feedbacks = conferenceService.findAllFeedbacks(id);
        } catch (ConferenceNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(feedbackConverter.entityToDto(feedbacks), HttpStatus.OK);
    }

}
