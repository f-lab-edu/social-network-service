package com.zudbs.project.service;

import java.util.List;
import com.zudbs.project.model.Alarm;

public interface AlarmService {

    void registerAlarm(Alarm alarm);

    Alarm getAlarm(int alarmId);

    List<Alarm> getAlarmList(String userId);

    void deleteAlarm(int alarmId);
}
