package com.zudbs.project.service;

import com.zudbs.project.model.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    void joinUser(User user);

    void login(User user, HttpSession httpSession);
  
    void deleteUser(User user);

}
