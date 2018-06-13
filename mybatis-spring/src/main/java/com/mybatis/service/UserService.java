package com.mybatis.service;

import com.mybatis.mapper.UserMapper;
import com.mybatis.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZJH on 2017/8/1.
 */
@Service
public class UserService {

    private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Integer id) throws Exception {
        return userMapper.findUserById(id);
    }

    @Transactional
    public void updateUser(User user) throws Exception {
        userMapper.updateUser(user);
    }

}
