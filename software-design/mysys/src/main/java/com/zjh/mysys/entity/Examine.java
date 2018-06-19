package com.zjh.mysys.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *
 * 待审核用户
 */
@Entity
@Table(name = "examine")
public class Examine {
    @Id
    @GenericGenerator(name = "NativeTableGenerator", strategy = "native")
    @GeneratedValue(generator = "NativeTableGenerator")
    @Column(name = "examine_id")
    private int examineId;

    @Column(length = 32)
    private String name;

    @Column(length = 16)
    private String password;

    @Column(length = 16)
    private String qq;

    @Column(length = 32)
    private String weChat;

    @Column(length = 16)
    private String phone;

    @Column(length = 128)
    private String address;

    @Column(length = 128)
    private String hobby;

    @Column(length = 256)
    private String introduction;

    @Column(length = 256)
    private String reason;

    @Column(length = 64)
    private String mail;

    @Column(length = 32)
    private String nickname;

    @Column
    private Integer sex;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getExamineId() {
        return examineId;
    }

    public void setExamineId(int examineId) {
        this.examineId = examineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
