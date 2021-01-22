package com.vironit.conferencemanagmentsystem.service.impl;

import com.vironit.conferencemanagmentsystem.exception.FeedbackServiceException;
import com.vironit.conferencemanagmentsystem.exception.RegistrationRequestNotFoundException;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.model.Feedback;
import com.vironit.conferencemanagmentsystem.model.RegistrationRequest;
import com.vironit.conferencemanagmentsystem.repository.ConferenceRepository;
import com.vironit.conferencemanagmentsystem.repository.FeedbackRepository;
import com.vironit.conferencemanagmentsystem.repository.RequestRepository;
import com.vironit.conferencemanagmentsystem.service.FeedbackLogic;
import com.vironit.conferencemanagmentsystem.service.FeedbackService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@Transactional(rollbackOn = Exception.class)
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository feedbackRepository;
    private RequestRepository requestRepository;
    private FeedbackLogic feedbackLogic;

    @Autowired
    public void setFeedbackRepository(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Autowired
    public void setFeedbackLogic(FeedbackLogic feedbackLogic) {
        this.feedbackLogic = feedbackLogic;
    }

    @Override
    public void sendFeedback(String content, String registrationCode) throws FeedbackServiceException {
        log.info("Send feedback: {}", content);
        try {
            RegistrationRequest registrationRequest = requestRepository.findByParticipantCode(registrationCode).orElseThrow(
                    () -> new RegistrationRequestNotFoundException("Registration request not found by code " + registrationCode));

            Conference conference = registrationRequest.getConference();
            Feedback feedback = new Feedback(feedbackLogic.getConvertAuthorName(registrationRequest.getFirstName(), registrationRequest.getSecondName()),
                    content, conference);
            feedbackRepository.saveAndFlush(feedback);
        } catch (RegistrationRequestNotFoundException exception) {
            log.error("Cannot update conference with id: {}, exception: {}", registrationCode, exception);
            throw new FeedbackServiceException("Cannot send feedback because you don't have registration code " + registrationCode, exception);
        }
    }
}
