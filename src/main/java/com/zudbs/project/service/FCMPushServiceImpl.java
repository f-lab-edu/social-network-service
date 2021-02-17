package com.zudbs.project.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*
    ConcurrentHashMap
    Multi-Thread 환경을 지원하는 Map이다.
    HashTable과 달리 메소드 단위로 잠금을 수행하는 것이 아닌
    Concurrency 레벨에 기반하여 map을 여러 파트로 나누어 두고,
    업데이트 동작이 일어나는 동안 오직 그 부분에만 잠금을 수행한다.

    HashMap -> Thread-safe X,

    HashTable -> Thread-safe O
    (put,get 메서드를 synchronizeded 하게 구현하여 멀티 스레드를 지원한다.)
   */
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
