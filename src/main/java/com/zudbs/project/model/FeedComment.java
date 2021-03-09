package com.zudbs.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class FeedComment {

    int id;

    int feedId;

    String userId;

    String comment;

    public FeedComment(int id, int feedId, String userId, String comment) {
        this(feedId, userId, comment);
        this.id = id;
    }

    public FeedComment(int feedId, String userId, String comment) {
        this.feedId = feedId;
        this.userId = userId;
        this.comment = comment;
    }

}
