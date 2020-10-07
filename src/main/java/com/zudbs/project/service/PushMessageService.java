package com.zudbs.project.service;

public interface PushMessageService {

    void sendPushMessage(String receiver, String title, String content);
}
