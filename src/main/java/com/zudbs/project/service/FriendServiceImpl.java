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
    @Override
    public void requestFriend(String userId, String friendId) {
        friendMapper.requestFriend(userId, friendId);

        Alarm alarm = new Alarm(userId, friendId, AlarmType.REQUEST_FRIEND,
                LocalDateTime.now(), false);

        alarmService.registerAlarm(alarm);
    }
}
