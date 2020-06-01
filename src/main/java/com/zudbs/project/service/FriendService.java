package com.zudbs.project.service;

public interface FriendService {

    void requestFriend(String userId, String friendId);

    void acceptFriend(String requestId, String userId);

    void rejectFriend(String requestId, String userId);

}
