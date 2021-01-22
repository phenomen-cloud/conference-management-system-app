package com.vironit.conferencemanagmentsystem.dto.converter.impl;

import com.vironit.conferencemanagmentsystem.dto.ConferenceRoomDto;
import com.vironit.conferencemanagmentsystem.dto.converter.Converter;
import com.vironit.conferencemanagmentsystem.model.ConferenceRoom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomConverter implements Converter<ConferenceRoomDto, ConferenceRoom> {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ConferenceRoomDto entityToDto(ConferenceRoom conferenceRoom) {
        return modelMapper.map(conferenceRoom, ConferenceRoomDto.class);
    }

    @Override
    public List<ConferenceRoomDto> entityToDto(List<ConferenceRoom> conferenceRooms) {
        return conferenceRooms.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ConferenceRoom dtoToEntity(ConferenceRoomDto conferenceRoomDto) {
        return modelMapper.map(conferenceRoomDto, ConferenceRoom.class);
    }

    @Override
    public List<ConferenceRoom> dtoToEntity(List<ConferenceRoomDto> conferenceRoomDtos) {
        return conferenceRoomDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
