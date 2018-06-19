package com.zjh.mysys.controller;

import com.zjh.mysys.entity.User;
import com.zjh.mysys.service.ManagerService;
import com.zjh.mysys.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping(method = RequestMethod.GET)
    public String managerPage(Model model) {
        return "manager/manager";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        return "manager/add";
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public String findUser(Model model) {
        return "manager/findByManager";
    }

    @RequestMapping(value = "/frozenUser", method = RequestMethod.GET)
    public String frozenUser(Model model) {
        return "manager/frozenUser";
    }

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(User user){
        try {
            return managerService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(User user){
        try {
            return managerService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(int userId ){
        try {
            return managerService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/frozenUser", method = RequestMethod.POST)
    public String frozenUser(int userId, int days){
        try {
            return managerService.frozenUser(userId, days);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }

}
