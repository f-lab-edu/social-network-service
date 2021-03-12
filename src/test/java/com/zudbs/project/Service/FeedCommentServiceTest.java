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

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FeedCommentServiceTest {

    @Autowired
    FeedService feedService;

    @Autowired
    FeedMapper feedMapper;

    @Test
    @DisplayName("댓글 추가 시 데이터가 정상적으로 데이터베이스에 저장되어야한다")
    public void addComment() {

        FeedComment feedComment = new FeedComment(2, "user", "Comment");

        feedService.addFeedComment(feedComment);

        FeedComment selectComment = feedMapper.getFeedCommnet(feedComment.getId());

        assertEquals(feedComment.getUserId(), selectComment.getUserId());
        assertEquals(feedComment.getComment(), selectComment.getComment());

    }

    @Test
    @DisplayName("댓글 삭제시 데이터가 데이터베이스에서 삭제되어야한다")
    public void deleteComment() {

        int CommentId = 7;

        FeedComment Comment = feedMapper.getFeedCommnet(CommentId);

        feedService.deleteFeedComment(CommentId);

        FeedComment deleteComment = feedMapper.getFeedCommnet(CommentId);

        assertNotNull(Comment);
        assertNull(deleteComment);

    }

}
