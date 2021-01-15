package com.zudbs.project.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Feed implements Serializable {

    private int id;

    private String userId;

    private LocalDateTime dateTime;

    private String content;

    private boolean hasFile;

    private List<File> files;

    private List<String> likes;

    public Feed(){}

    public Feed(int id, String userId, LocalDateTime dateTime, String content, boolean hasFile, List<String> likes) {
        this(id, userId, dateTime, content, hasFile);
        this.likes = likes;
    }

    public Feed(int id, String userId, LocalDateTime dateTime, String content, boolean hasFile) {
        this(userId, dateTime, content, hasFile);
        this.id = id;
    }

    public Feed(String userId, LocalDateTime dateTime, String content, boolean hasFile) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
        this.hasFile = hasFile;
    }


}
