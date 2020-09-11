package com.zudbs.project.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Feed {

    private int id;

    private String writerId;

    private String userId;

    private LocalDateTime date;

    private String content;

    private boolean hasFile;
}
