package com.vironit.conferencemanagmentsystem.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackLogicImplTest {
    private static final FeedbackLogicImpl feedbackLogic = new FeedbackLogicImpl();

    @Test
    void getConvertAuthorName() {
        assertEquals("Anna M*********", feedbackLogic.getConvertAuthorName("Anna", "Mayakovsky"));
    }
}