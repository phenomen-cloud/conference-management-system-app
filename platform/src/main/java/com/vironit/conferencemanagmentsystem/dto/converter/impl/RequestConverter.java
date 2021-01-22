package com.vironit.conferencemanagmentsystem.dto.converter.impl;

import com.vironit.conferencemanagmentsystem.dto.RegistrationRequestDto;
import com.vironit.conferencemanagmentsystem.dto.converter.Converter;
import com.vironit.conferencemanagmentsystem.model.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestConverter implements Converter<RegistrationRequestDto, RegistrationRequest> {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RegistrationRequestDto entityToDto(RegistrationRequest registrationRequest) {
        return modelMapper.map(registrationRequest, RegistrationRequestDto.class);
    }

    @Override
    public List<RegistrationRequestDto> entityToDto(List<RegistrationRequest> registrationRequestList) {
        return registrationRequestList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public RegistrationRequest dtoToEntity(RegistrationRequestDto registrationRequestDto) {
        return modelMapper.map(registrationRequestDto, RegistrationRequest.class);
    }

    @Override
    public List<RegistrationRequest> dtoToEntity(List<RegistrationRequestDto> registrationRequestDtos) {
        return registrationRequestDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
