package com.vironit.conferencemanagmentsystem.dto;

import com.vironit.conferencemanagmentsystem.dto.transfer.New;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class FeedbackDto {
    @Null(groups = {New.class})
    private Long id;
    @NotNull(groups = {New.class})
    private String author;
    @NotNull(groups = {New.class})
    private String content;
    @NotNull(groups = {New.class})
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
