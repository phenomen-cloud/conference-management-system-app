package com.vironit.conferencemanagmentsystem.controller;

import com.vironit.conferencemanagmentsystem.dto.FeedbackDto;
import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import com.vironit.conferencemanagmentsystem.exception.FeedbackServiceException;
import com.vironit.conferencemanagmentsystem.service.FeedbackService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
@Api(value = "Feedback controller", description = "enable operations with feedback inside Conference-gateway")
public class FeedbackController {
    private FeedbackService feedbackService;

    @Autowired
    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping(path = "/send",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackDto> sendFeedback(@RequestBody @Validated(New.class) FeedbackDto feedbackDto) {
        try {
            feedbackService.sendFeedback(feedbackDto.getContent(), feedbackDto.getCode());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FeedbackServiceException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
