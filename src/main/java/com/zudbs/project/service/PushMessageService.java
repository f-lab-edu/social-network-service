package com.zudbs.project.service;

public interface PushMessageService {

    void registerReceiver(String userId, String receiver);

    void removeReceiver(String userId);

    void sendPushMessage(String receiver, String title, String content);
}
