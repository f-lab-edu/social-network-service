package com.zudbs.project.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class FCMPushServiceImpl implements PushMessageService {

    @Value("${firebaseConfigPath}")
    private String firebaseConfigPath;

    private Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @PostConstruct //의존성 주입 후 초기화를 수행하는 메서드
    public void initialize() throws IOException {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials
                        .fromStream(new ClassPathResource(firebaseConfigPath)
                                .getInputStream()))
                .build();

        FirebaseApp.initializeApp(options);

    }

    public void registerReceiver(String userId, String receiver) {

        tokenMap.put(userId, receiver);
    }

    public void removeReceiver(String userId) {
        tokenMap.remove(userId);
    }

    public void sendPushMessage(String reciver, String title, String content) {
        Message message = Message.builder()
                .setToken(reciver)
                .setWebpushConfig(
                        WebpushConfig.builder()
                                .setNotification(new WebpushNotification(title, content))
                                .build())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
