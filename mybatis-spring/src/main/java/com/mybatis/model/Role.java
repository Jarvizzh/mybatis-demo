package com.mybatis.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZJH on 2017/8/2.
 */
public class Role implements Serializable {

    private Integer roleId;
    private String roleName;
    private List<User> users;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }
}
