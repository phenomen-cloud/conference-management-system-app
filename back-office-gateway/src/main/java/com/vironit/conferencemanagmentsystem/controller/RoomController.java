package com.vironit.conferencemanagmentsystem.controller;

import com.vironit.conferencemanagmentsystem.dto.ConferenceRoomDto;
import com.vironit.conferencemanagmentsystem.dto.converter.impl.RoomConverter;
import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateCapacity;
import com.vironit.conferencemanagmentsystem.dto.transfer.UpdateStatus;
import com.vironit.conferencemanagmentsystem.exception.RoomServiceException;
import com.vironit.conferencemanagmentsystem.model.ConferenceRoom;
import com.vironit.conferencemanagmentsystem.service.RoomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@Api(value = "Room controller", description = "enable operations with rooms inside Back-Office-gateway")
public class RoomController {
    private RoomService roomService;
    private RoomConverter roomConverter;

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setRoomConverter(RoomConverter roomConverter) {
        this.roomConverter = roomConverter;
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceRoomDto> createRoom(@RequestBody @Validated(New.class) ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoom = roomService.createRoom(conferenceRoomDto.getName(), conferenceRoomDto.getLocation(), conferenceRoomDto.getCapacity());
        return new ResponseEntity<>(roomConverter.entityToDto(conferenceRoom), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/update/capacity",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceRoomDto> updateCapacity(@PathVariable Long id,
                                                            @RequestBody @Validated(UpdateCapacity.class) ConferenceRoomDto conferenceRoomDto) {
        try {
            roomService.updateCapacity(id, conferenceRoomDto.getCapacity());
        } catch (RoomServiceException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/update/status",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceRoomDto> updateDateTime(@PathVariable Long id,
                                                            @RequestBody @Validated(UpdateStatus.class) ConferenceRoomDto conferenceRoomDto) {
        try {
            roomService.updateStatus(id, conferenceRoomDto.getStatus());
        } catch (RoomServiceException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
