package com.zudbs.project.model;

import lombok.Getter;
import lombok.Setter;

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
}
