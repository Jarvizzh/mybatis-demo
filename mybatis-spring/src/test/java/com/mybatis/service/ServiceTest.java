package com.mybatis.service;

import com.mybatis.mapper.*;
import com.mybatis.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by ZJH on 2017/8/1.
 */
@Transactional
public class ServiceTest extends AbstractTest{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Test
    @Rollback(false)
    public void testFindUserById() throws Exception {
        User user = new User();
        user.setId(2);
        user.setSex("male");
        user.setAddress("aaa");
        user.setName("l6666");
        userMapper.updateUser(user);
//        System.out.println(userMapper.findUserById(3));
    }

    @Test
    public void testLazyLoading() throws Exception {
        List<Order> orders = orderMapper.findAllOrderWithUserInfo();
        Order order = orders.get(0);
        User user = order.getUser();
        Set<OrderDetail> details = order.getOrderDetails();
        System.out.println(details.size());
    }

    @Test
    public void t(){
//        List<User> users = userMapper.findUserByNameOrderByColumn("%z%","id");
        try {
            List<Order> orders = orderMapper.findOrderWithDetailByUserId(3);
            System.out.println(orders);
            Set<OrderDetail> details = orders.get(0).getOrderDetails();
            System.out.println(details.size());
//            Item item = details.get(0).getItem();
//            System.out.println(item.getItemName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test1(){
        try {
            List<User> users = userMapper.findAllUsersWithRoles();
            users.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            List<Role> roles = roleMapper.findAllRoleWithUser();
            roles.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
