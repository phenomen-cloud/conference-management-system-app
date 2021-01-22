package com.vironit.conferencemanagmentsystem.dto;

import com.vironit.conferencemanagmentsystem.dto.transfer.FindAvailable;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class DateDto {
    @NotNull(groups = {FindAvailable.class})
    private LocalDateTime startTime;
    @NotNull(groups = {FindAvailable.class})
    protected LocalDateTime endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
