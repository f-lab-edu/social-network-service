package com.zudbs.project.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendMapper {
    void requestFriend(String userId, String friendId);

    void acceptFriend(String requestId, String userId);

    void rejectFriend(String requestId, String userId);

    void saveRejectHistory(String requestId, String rejectId);
}
