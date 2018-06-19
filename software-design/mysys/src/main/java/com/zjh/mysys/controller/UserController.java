package com.zjh.mysys.controller;

import com.zjh.mysys.entity.Examine;
import com.zjh.mysys.service.UserService;
import com.zjh.mysys.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "user/login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userPage(Model model) {
        return "user/user";
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public String findUser(Model model) {
        return "user/findByUser";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String name, @RequestParam String password, @RequestParam Integer type, Model model) {
        try {
            return userService.login(name, password, type, model);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Examine examine) {
        try {
            return userService.register(examine);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }


}