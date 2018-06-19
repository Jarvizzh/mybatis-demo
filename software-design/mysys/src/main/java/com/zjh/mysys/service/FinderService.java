package com.zjh.mysys.service;

import com.zjh.mysys.entity.User;
import com.zjh.mysys.repository.UserRepository;
import com.zjh.mysys.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinderService {
    @Autowired
    private UserRepository userRepository;

    public String byName(String name) {
        List<User> users = userRepository.findUserByName(name);
        return GsonUtil.getSuccessJson(users);
    }

    public String byPhone(String phone) {
        List<User> users = userRepository.findUserByPhone(phone);
        return GsonUtil.getSuccessJson(users);
    }

    public String byHobby(String hobby) {
        List<User> users = userRepository.findUserByHobby(hobby);
        return GsonUtil.getSuccessJson(users);
    }
}
