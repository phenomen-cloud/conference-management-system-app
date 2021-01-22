package com.vironit.conferencemanagmentsystem.service.impl;

import com.vironit.conferencemanagmentsystem.service.RegistrationLogic;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationLogicImpl implements RegistrationLogic {
    @Override
    public String getUniqueParticipantCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
