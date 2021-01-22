package com.vironit.conferencemanagmentsystem.dto.converter.impl;

import com.vironit.conferencemanagmentsystem.dto.FeedbackDto;
import com.vironit.conferencemanagmentsystem.dto.converter.Converter;
import com.vironit.conferencemanagmentsystem.model.Feedback;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackConverter implements Converter<FeedbackDto, Feedback> {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FeedbackDto entityToDto(Feedback feedback) {
        return modelMapper.map(feedback, FeedbackDto.class);
    }

    @Override
    public List<FeedbackDto> entityToDto(List<Feedback> feedbacks) {
        return feedbacks.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Feedback dtoToEntity(FeedbackDto feedbackDto) {
        return modelMapper.map(feedbackDto, Feedback.class);
    }

    @Override
    public List<Feedback> dtoToEntity(List<FeedbackDto> feedbackDtos) {
        return feedbackDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
