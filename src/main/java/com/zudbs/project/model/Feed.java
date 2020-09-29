package com.zudbs.project.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Feed {

    private int id;

    private String userId;

    private LocalDateTime dateTime;

    private String content;

    private boolean hasFile;
}
