package com.zudbs.project.model;

import lombok.Getter;

@Getter
public class FeedLike {
    int id;

    int feedId;

    String userId;

    public FeedLike(int id, int feedId,String userId)
    {
        this(feedId,userId);
        this.id=id;
    }

    public FeedLike(int feedId,String userId)
    {
        this.feedId=feedId;
        this.userId= userId;
    }

}
