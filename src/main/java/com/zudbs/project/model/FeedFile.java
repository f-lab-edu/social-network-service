package com.zudbs.project.model;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class FeedFile {
    int id;

    int feedId;

    String originalFileName;

    String storedFileName;

    long FileSize;

}
