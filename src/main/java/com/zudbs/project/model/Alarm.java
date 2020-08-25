package com.zudbs.project.model;

import com.zudbs.project.Type.AlarmType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Alarm {

    private  int id;

    private String userId;

    private String opponentId;

    private LocalDateTime time;

    private AlarmType alarmType;

    private boolean alarmStatus;
}
