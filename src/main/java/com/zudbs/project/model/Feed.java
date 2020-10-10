package com.zudbs.project.model;

import lombok.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Feed {

    private int id;

    private String userId;

    private LocalDateTime dateTime;

    private String content;

    private boolean hasFile;

    private List<File> files;

    public Feed(int id, String userId, LocalDateTime dateTime, String content, boolean hasFile) {
        this.id = id;
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
        this.hasFile = hasFile;
    }

    public Feed(int feedId, String userId, LocalDateTime now, String content) {
        this.id = id;
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
    }

    public Feed(String userId, LocalDateTime dateTime, String content) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
    }


}
