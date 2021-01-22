package com.vironit.conferencemanagmentsystem.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationLogicImplTest {
    private static final RegistrationLogicImpl registrationLogic = new RegistrationLogicImpl();

    @Test
    void getUniqueParticipantCode() {
        assertNotEquals(registrationLogic.getUniqueParticipantCode(), registrationLogic.getUniqueParticipantCode());
    }
}