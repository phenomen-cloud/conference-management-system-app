package com.vironit.conferencemanagmentsystem;

import com.vironit.conferencemanagmentsystem.enums.ConferenceStatus;
import com.vironit.conferencemanagmentsystem.model.Conference;
import com.vironit.conferencemanagmentsystem.repository.ConferenceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BackOfficeApplicationTest {
    private MockMvc mockMvc;
    private ConferenceRepository conferenceRepository;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Test
    public void cancelConference() throws Exception {
        mockMvc.perform(get("/conference/{id}/cancel", 1L)).andExpect(status().isOk());
        Conference conference = conferenceRepository.findById(1L).orElse(null);

        assertEquals(conference.getStatus(), ConferenceStatus.CANCELED);
    }
}