package com.zudbs.project.service;

import com.zudbs.project.model.User;

public interface UserService {

     void joinUser(User user);

     boolean deleteUser(User user);
}