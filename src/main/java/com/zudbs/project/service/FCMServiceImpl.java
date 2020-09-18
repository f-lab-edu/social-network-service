package com.zudbs.project.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class FCMServiceImpl implements FCMService {

    private String firebaseConfigPath = "resources/firebase/firebase_service_key.json";

    private Map<String, String> tokenMap = new HashMap<>();

    @PostConstruct
    public void initialize() throws IOException {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials
                        .fromStream(new ClassPathResource(firebaseConfigPath)
                                .getInputStream()))
                .build();

        FirebaseApp.initializeApp(options);

    }

    public void registerToken(String userId, String token) {
        tokenMap.put(userId, token);
    }

    public void sendPushMessage(String token, String title, String content) {
        Message message = Message.builder()
                .setToken(token)
                .setWebpushConfig(
                        WebpushConfig.builder()
                                .setNotification(new WebpushNotification(title, content))
                                .build())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
