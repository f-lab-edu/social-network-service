package com.zudbs.project.service;

import com.zudbs.project.mapper.AlarmMapper;
import com.zudbs.project.model.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmMapper alarmMapper;

    @Override
    public void registerAlarm(Alarm alarm) {
        alarmMapper.registerAlarm(alarm);
    }

    @Override
    public Alarm getAlarm(int alarmId) {

        Alarm alarm = alarmMapper.getAlarm(alarmId);

        if (alarm.isAlarmStatus() == false) {

            alarm.setAlarmStatus(true);
            alarmMapper.readAlarm(alarmId);
        }

        return alarm;
    }

    @Override
    public List<Alarm> getAlarmList(String userID) {
        return alarmMapper.getAlarmList(userID);
    }

    @Override
    public void deleteAlarm(int alarmId) {
        alarmMapper.deleteAlarm(alarmId);
    }
}
