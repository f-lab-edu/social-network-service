package com.zudbs.project.service;

import com.zudbs.project.mapper.UserMapper;
import com.zudbs.project.model.User;
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
    public boolean deleteUser(User user) {
        int result = userMapper.deleteUser(user);

        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean login(User user, HttpSession httpSession) {
        User loginUser = userMapper.selectUser(user);

        if (loginUser == null) {
            httpSession.setAttribute("user", loginUser);
            return true;
        }

        return false;
    }

}
