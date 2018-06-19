package com.zjh.mysys.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GenericGenerator(name = "NativeTableGenerator", strategy = "native")
    @GeneratedValue(generator = "NativeTableGenerator")
    @Column(name = "authority_id")
    private int authorityId;

    @Column(length = 32)
    private String name;

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
