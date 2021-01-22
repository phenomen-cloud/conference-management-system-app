package com.vironit.conferencemanagmentsystem.service.impl;

import com.vironit.conferencemanagmentsystem.service.FeedbackLogic;
import org.springframework.stereotype.Service;

@Service
public class FeedbackLogicImpl implements FeedbackLogic {
    @Override
    public String getConvertAuthorName(String authorFirstName, String authorSecondName) {
        char[] secondCodedName = authorSecondName.toCharArray();
        for (int i = 1; i < secondCodedName.length; i ++) {
            secondCodedName[i] = '*';
        }
        return authorFirstName + " "+ new String(secondCodedName);
    }
}
