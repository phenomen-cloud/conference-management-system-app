package com.vironit.conferencemanagmentsystem.dto.converter.impl;

import com.vironit.conferencemanagmentsystem.dto.ConferenceDto;
import com.vironit.conferencemanagmentsystem.dto.converter.Converter;
import com.vironit.conferencemanagmentsystem.model.Conference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConferenceConverter implements Converter<ConferenceDto, Conference> {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ConferenceDto entityToDto(Conference conference) {
        return modelMapper.map(conference, ConferenceDto.class);
    }

    @Override
    public List<ConferenceDto> entityToDto(List<Conference> conferences) {
        return conferences.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Conference dtoToEntity(ConferenceDto conferenceDto) {
        return modelMapper.map(conferenceDto, Conference.class);
    }

    @Override
    public List<Conference> dtoToEntity(List<ConferenceDto> conferenceDtos) {
        return conferenceDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
