package com.zjh.mysys.controller;

import com.zjh.mysys.service.FinderService;
import com.zjh.mysys.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/finder")
public class FinderController {
    @Autowired
    private FinderService finderService;

    @ResponseBody
    @RequestMapping(value = "byName/{name}", method = RequestMethod.GET)
    public String byName(@PathVariable String name){
        try {
            return finderService.byName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "byPhone/{phone}", method = RequestMethod.GET)
    public String byPhone(@PathVariable String phone){
        try {
            return finderService.byPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "byHobby/{hobby}", method = RequestMethod.GET)
    public String byHobby(@PathVariable String hobby){
        try {
            return finderService.byHobby(hobby);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtil.getErrorJson(e.getMessage());
        }
    }
}
