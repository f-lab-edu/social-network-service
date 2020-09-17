package com.zudbs.project.model;

import com.zudbs.project.Type.AlarmType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Alarm {

    private int id;

    private String userId;

    private String opponentId;

    private LocalDateTime time;

    private AlarmType alarmType;

    @Setter
    private boolean alarmStatus;

    public Alarm(int id, String userId, String opponentId, AlarmType alarmType, LocalDateTime time, boolean alarmStatus) {
        this(userId, opponentId, alarmType, time, alarmStatus);
        this.id = id;
    }

    public Alarm(String userId, String opponentId, AlarmType alarmType, LocalDateTime time, boolean alarmStatus) {
        this.userId = userId;
        this.opponentId = opponentId;
        this.alarmType = alarmType;
        this.time = time;
        this.time = time;
        this.alarmStatus = alarmStatus;
    }
}
