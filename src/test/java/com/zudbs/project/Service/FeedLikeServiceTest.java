package com.zudbs.project.Service;

import com.zudbs.project.SnsProjectApplication;
import com.zudbs.project.config.WebConfig;
import com.zudbs.project.mapper.FeedMapper;
import com.zudbs.project.model.FeedLike;
import com.zudbs.project.service.FeedService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/*
SpringBootTest

통합테스트를 제공하는 어노테이션 실제 구동되는 애플리케이션과 같이
애플리케이션 컨텍스트를 로드하여 테스트한다. 애플리케이션의 설정을 모드 로드하기
때문에 규모가 클수록 느려진다.

RunWith(SpringRunner.class)
SpringBoot테스트 기능과 Junit을 열결해주는 어노테이션이다.
SpringBoot테스트기능을 사용하기위해서는 반드시 필요하다.

Transactional
테스트를 실행할때마다 트랜잭션을 시작하고, 테스트가 끝나면 RollBack 한다.
*/
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FeedLikeServiceTest {

    @Autowired
    FeedService feedService;

    @Autowired
    FeedMapper feedMapper;

     int feedId;
     FeedLike feedLike;

     /*
     BeforeClass : 테스트 클래스 시작시 1번만 실행된다.
     Before      : 각 테스트 케이스마다 실행된다.
     After       : 각 테스트 케이스가 실행된 후 실행된다.
     AfterClass  : 모든 테스트 케이스가 실행된 후 1번만 실행된다.
     */
    @BeforeClass
    public  void initialize(){
        feedId=2;
        feedLike=new FeedLike(feedId,"user");
    }

    @Test
    public void addLike(){

        int likesCount= feedMapper.getFeedLikesCount(feedId);

        feedService.addFeedLike(feedLike);

        int newLikesCount =feedMapper.getFeedLikesCount(feedId);

        assertEquals(likesCount+1,newLikesCount);
    }

    @Test
    public void cancelLike(){

        addLike();

        int likesCount= feedMapper.getFeedLikesCount(feedId);

        feedService.cancelFeedLike(feedLike);

        int newLikesCount =feedMapper.getFeedLikesCount(feedId);

        assertEquals(likesCount-1,newLikesCount);
    }
}
