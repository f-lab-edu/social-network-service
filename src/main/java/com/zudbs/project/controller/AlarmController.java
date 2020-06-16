package com.zudbs.project.controller;

import com.zudbs.project.model.Alarm;
import com.zudbs.project.model.User;
import com.zudbs.project.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/alarms")
public class AlarmController {

    @Autowired
    AlarmService alarmService;

    /*ResponseEntity  응답을 한번 더 감싸는 역할을 한다. 이러한 응답을 받은 Client 개발자는 요청 후
    응답의 상태코드에 따라 다른 화면을 노출시킬 수도있고, Header값에 따라 다른 동작을 할 수도 있다. */
    @GetMapping
    public ResponseEntity<List<Alarm>> getAlarmList(HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");

        List<Alarm> alarmList = alarmService.getAlarmList(user.getUserID());

        return ResponseEntity.ok(alarmList);
    }

    @GetMapping("/{alarmId}")
    public ResponseEntity<Alarm> readAlarm(@PathVariable int alarmId) {

        Alarm alarm = alarmService.getAlarm(alarmId);

        return ResponseEntity.ok(alarm);
    }

    @DeleteMapping("/{alarmId}")
    public HttpStatus deleteAlarm(@PathVariable int alarmId) {

        alarmService.deleteAlarm(alarmId);

        return HttpStatus.OK;
    }

}
