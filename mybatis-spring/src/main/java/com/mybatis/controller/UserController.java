package com.mybatis.controller;

import com.google.gson.Gson;
import com.mybatis.model.User;
import com.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZJH on 2017/8/1.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/get/{userId}")
    public String findUser(@PathVariable Integer userId) throws Exception {
        Gson gson = new Gson();
        return gson.toJson(userService.getUserById(userId));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{userId}/{name}")
    public String findUser(@PathVariable Integer userId,
                           @PathVariable String name) {
        try {
            User user = userService.getUserById(userId);
            user.setName(name);
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

}
