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

    * Transactional Options
     1. isolation (격리 수준)
        트랜잭션에서 일관성이 없는 데이터를 허용하도록 하는 수준

     2. propagation(전파 옵션)
        트랜잭션 동작 도중 다른 트랜잭션을 실행하는 상황에 관련된 옵션

     3. readOnly
        트랜잭션을 읽기 전용 => 성능을 최적화 또는 특정 쓰기 작업이 일어나는 것을 의도적으로 방지하기 위해

     4. rollback-for, rollbackFor, rollbackForClassName
        @Transactional는 Checked 예외 롤백 진행 X => Checked 예외 롤백 진행하기 위한 옵션

     5. timeout
        지정한 시간 내에 해당 메소드 수행이 완료되이 않은 경우 rollback


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

    @Override
    public void acceptFriend(String requestId, String userId) {
        friendMapper.acceptFriend(requestId, userId);
    }

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
    public void rejectFriend(String requestId, String userId) {
        friendMapper.rejectFriend(requestId, userId);
        friendMapper.saveRejectHistory(requestId, userId);
    }

    @Override
    public void followFriend(String requestId, String userId, boolean follow) {
        friendMapper.followFriend(requestId, userId, follow);
    }

}
