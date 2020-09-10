package com.zudbs.project.model;

import com.zudbs.project.Type.AlarmType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/*
Alarm 객체는 수정할 경우가 없는 클래스이다.
Setter을 작성하지 않고 immutable(불변객체)로 구현한다.
*/
@Getter
public class Alarm {

    private int id;

    private String userId;

    private String opponentId;

    private LocalDateTime time;

    private AlarmType alarmType;

    private boolean alarmStatus;

    public Alarm(int id, String userId, String opponentId, AlarmType alarmType, LocalDateTime time, boolean alarmStatus) {
        this.id = id;
        this.userId = userId;
        this.opponentId = opponentId;
        this.alarmType = alarmType;
        this.time = time;
        this.time = time;
        this.alarmStatus = alarmStatus;
    }

    public Alarm(String userId, String opponentId, AlarmType alarmType, LocalDateTime time, boolean alarmStatus) {
        this(-1, userId, opponentId, alarmType, time, alarmStatus);
    }
}
