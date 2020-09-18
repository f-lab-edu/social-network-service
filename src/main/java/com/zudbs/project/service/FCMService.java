package com.zudbs.project.service;

public interface FCMService {

     void registerToken(String userId, String token) ;

     void sendPushMessage(String token, String title, String content) ;
}
