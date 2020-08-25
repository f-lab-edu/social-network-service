package com.zudbs.project.mapper;

import com.zudbs.project.model.Alarm;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AlarmMapper {

    void registerAlarm(Alarm alarm);

    Alarm getAlarm(int alarmId);

    void readAlarm(int alarmId);

    List<Alarm> getAlarmList(String userId);

    void deleteAlarm(int alarmId);
}
