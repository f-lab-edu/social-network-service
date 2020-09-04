package com.zudbs.project.service;

import com.zudbs.project.mapper.UserMapper;
import com.zudbs.project.model.User;
import com.zudbs.project.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void joinUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        int result = userMapper.deleteUser(user);

        if (result == 0) {
            throw new IllegalArgumentException("올바른 사용자가 아닙니다.");
        }
    }

    @Override
    public void login(User user, HttpSession httpSession) {
        User loginUser = userMapper.selectUser(user);

        if (loginUser == null) {
            throw new IllegalArgumentException("ID 또는 PW가 올바르지 않습니다.");
        }

        httpSession.setAttribute(SessionUtil.LOGIN_SESSION_KEY, loginUser.getUserID());
    }

}
