package com.zudbs.project.service;

import com.zudbs.project.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public void requestFriend(String userId, String friendId) {
        friendMapper.requestFriend(userId, friendId);
    }

    @Override
    public void acceptFriend(String requestId, String userId) {
        friendMapper.acceptFriend(requestId, userId);
    }

    @Override
    public void rejectFriend(String requestId, String userId) {
        friendMapper.rejectFriend(requestId, userId);
        friendMapper.saveRejectHistory(requestId, userId);
    }

}
