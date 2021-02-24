package com.zudbs.project.service;

public interface PushMessageService {

    void sendPushMessage(String receiver, String title, String content);

    void registerReceiver(String userId, String receiver);

    void removeReceiver(String userId);
}
