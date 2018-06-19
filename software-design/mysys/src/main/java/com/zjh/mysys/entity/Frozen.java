package com.zjh.mysys.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "frozen")
public class Frozen {
    @Id
    @GenericGenerator(name = "NativeTableGenerator", strategy = "native")
    @GeneratedValue(generator = "NativeTableGenerator")
    @Column(name = "frozen_id")
    private int frozenId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Date endDate;

    public int getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(int frozenId) {
        this.frozenId = frozenId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
