package com.zudbs.project.Service;

import com.zudbs.project.mapper.FeedMapper;
import com.zudbs.project.model.FeedComment;
import com.zudbs.project.model.FeedLike;
import com.zudbs.project.service.FeedService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FeedCommentServiceTest {

    @Autowired
    FeedService feedService;

    @Autowired
    FeedMapper feedMapper;

    @Test
    @DisplayName("댓글 추가시 댓글 내용 비교")
    public void addComment() {

        FeedComment feedComment = new FeedComment(2, "user", "Comment");

        feedService.addFeedComment(feedComment);

        FeedComment selectComment = feedMapper.getFeedCommnet(feedComment.getId());

        assertEquals(feedComment.getUserId(), selectComment.getUserId());
        assertEquals(feedComment.getComment(), selectComment.getComment());

    }

}
