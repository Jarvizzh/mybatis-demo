package com.zjh.mysys.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GenericGenerator(name = "NativeTableGenerator", strategy = "native")
    @GeneratedValue(generator = "NativeTableGenerator")
    @Column(name = "manager_id")
    private int managerId;

    @Column(length = 32)
    private String name;

    @Column(length = 16)
    private String password;
}
