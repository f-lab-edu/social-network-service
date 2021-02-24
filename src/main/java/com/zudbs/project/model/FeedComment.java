package com.zudbs.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class FeedFile {
    int id;

    int feedId;

    String originalFileName;

    String storedFileName;

    long FileSize;

}
