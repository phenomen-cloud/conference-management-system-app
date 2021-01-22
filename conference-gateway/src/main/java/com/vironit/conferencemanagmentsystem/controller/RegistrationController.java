package com.vironit.conferencemanagmentsystem.controller;

import com.vironit.conferencemanagmentsystem.dto.ConferenceDto;
import com.vironit.conferencemanagmentsystem.dto.DateDto;
import com.vironit.conferencemanagmentsystem.dto.RegistrationRequestDto;
import com.vironit.conferencemanagmentsystem.dto.converter.impl.ConferenceConverter;
import com.vironit.conferencemanagmentsystem.dto.converter.impl.RequestConverter;
import com.vironit.conferencemanagmentsystem.dto.transfer.CancelRegistration;
import com.vironit.conferencemanagmentsystem.dto.transfer.FindAvailable;
import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import com.vironit.conferencemanagmentsystem.exception.RegistrationServiceException;
import com.vironit.conferencemanagmentsystem.model.RegistrationRequest;
import com.vironit.conferencemanagmentsystem.service.RegistrationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
@Api(value = "Registration controller", description = "enable operations with registration inside Conference-gateway")
public class RegistrationController {
    private RequestConverter requestConverter;
    private RegistrationService registrationService;
    private ConferenceConverter conferenceConverter;

    @Autowired
    public void setRequestConverter(RequestConverter requestConverter) {
        this.requestConverter = requestConverter;
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setConferenceConverter(ConferenceConverter conferenceConverter) {
        this.conferenceConverter = conferenceConverter;
    }

    @GetMapping(path = "/availableConferences",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConferenceDto>> findAvailableConferences(@RequestBody @Validated(FindAvailable.class) DateDto dateDto) {
        List<ConferenceDto> conferenceDtos = conferenceConverter.entityToDto(registrationService.findAvailableConferences(dateDto.getStartTime(), dateDto.getEndTime()));
        return new ResponseEntity<>(conferenceDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationRequestDto> registerForConference(@PathVariable Long id,
                                                                        @RequestBody @Validated(New.class) RegistrationRequestDto registrationRequestDto) {
        try {
            RegistrationRequest registrationRequest = registrationService.registerForConference(id,
                    registrationRequestDto.getFirstName(),
                    registrationRequestDto.getSecondName(),
                    registrationRequestDto.getBirthDate(),
                    registrationRequestDto.getGender(),
                    registrationRequestDto.getEmail());
            return new ResponseEntity<>(requestConverter.entityToDto(registrationRequest), HttpStatus.OK);
        } catch (RegistrationServiceException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/cancel",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationRequestDto> cancelRegistration(@RequestBody @Validated(CancelRegistration.class) RegistrationRequestDto registrationRequestDto) {
        try {
            registrationService.cancelRegistration(registrationRequestDto.getParticipantCode());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RegistrationServiceException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
