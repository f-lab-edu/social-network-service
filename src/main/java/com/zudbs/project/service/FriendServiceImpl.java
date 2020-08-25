package com.zudbs.project.service;

import com.zudbs.project.Type.AlarmType;
import com.zudbs.project.mapper.FriendMapper;
import com.zudbs.project.model.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public void requestFriend(String userId, String friendId) {

        friendMapper.requestFriend(userId,friendId);

        Alarm alarm =new Alarm();
        alarm.setUserId(userId);
        alarm.setOpponentId(friendId);
        alarm.setAlarmType(AlarmType.REQUEST_FRIEND);
        alarm.setTime(LocalDateTime.now());

        alarmService.registerAlarm(alarm);
    }
}
