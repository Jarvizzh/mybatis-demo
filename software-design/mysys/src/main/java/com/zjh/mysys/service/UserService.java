package com.zjh.mysys.service;

import com.zjh.mysys.entity.Examine;
import com.zjh.mysys.entity.Manager;
import com.zjh.mysys.entity.User;
import com.zjh.mysys.repository.ExamineRepository;
import com.zjh.mysys.repository.ManagerRepository;
import com.zjh.mysys.repository.UserRepository;
import com.zjh.mysys.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ExamineRepository examineRepository;

    @Transactional
    public String login(String name, String password, int type, Model model) {
        switch (type) {
            case 0 : {
                User user = userRepository.getUserByNameAndPass(name, password);
                if (user != null) {
                    model.addAttribute("user", user);
                    model.addAttribute("authorityList", user.getAuthorities());
                    model.addAttribute("type", 0);
                    return GsonUtil.getSuccessJson();
                }
            } break;
            case 1 : {
                Manager manager = managerRepository.getUserByNameAndPass(name, password);
                if (manager != null) {
                    model.addAttribute("user", manager);
                    model.addAttribute("type", 1);
                    return GsonUtil.getSuccessJson();
                }
            } break;
        }
        return GsonUtil.getErrorJson();
    }


    public String register(Examine examine) {
        examineRepository.save(examine);
        return GsonUtil.getSuccessJson();
    }
}