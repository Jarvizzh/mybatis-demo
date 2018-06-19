package com.zjh.mysys.service;

import com.zjh.mysys.entity.Frozen;
import com.zjh.mysys.entity.User;
import com.zjh.mysys.repository.FrozenRepository;
import com.zjh.mysys.repository.UserRepository;
import com.zjh.mysys.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ManagerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FrozenRepository frozenRepository;

    @Transactional
    public String addUser(User user) {
        userRepository.save(user);
        return GsonUtil.getSuccessJson();
    }

    @Transactional
    public String updateUser(User user) {
        user.setUserId(user.getUserId());
        return GsonUtil.getSuccessJson();
    }

    @Transactional
    public String deleteUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        frozenRepository.deleteByUserId(userId);
        userRepository.delete(user);
        return GsonUtil.getSuccessJson();
    }

    @Transactional
    public String frozenUser(int userId, int days) {
        Frozen frozen = new Frozen();
        frozen.setUser(new User(userId));
        Date date = new Date();
        date.setTime(date.getTime() + 86400000 * days);
        frozen.setEndDate(date);
        frozenRepository.save(frozen);
        return GsonUtil.getSuccessJson();
    }


}
