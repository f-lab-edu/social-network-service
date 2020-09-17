package com.zudbs.project.service;

import com.zudbs.project.Type.AlarmType;
import com.zudbs.project.mapper.FriendMapper;
import com.zudbs.project.model.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private FriendMapper friendMapper;

    @Transactional
    /*
    @Transactional: 선언적 트랙잭션
    클래스 혹은 메서드 위헤 해당 어노테이션이 추가되면, 해당하는 클래스에 트랙잭션 기능이 적용된 프록시 객체가 생성된다.
    프록시 객체는 트랙잭션이 적용된 메소드가 호출될 때 PlatformTransactionManager를 사용하여 트랜잭션을 시작하고
    정상 여부에 따라 Commit 또는 Rollback 한다.

    PlatformTransactionManager
    트랜잭션 기능을 추상화한 인터페이스이다.
     */
    @Override
    public void requestFriend(String userId, String friendId) {
        friendMapper.requestFriend(userId, friendId);

        Alarm alarm = new Alarm(userId, friendId, AlarmType.REQUEST_FRIEND,
                LocalDateTime.now(), false);

        alarmService.registerAlarm(alarm);
    }
}
