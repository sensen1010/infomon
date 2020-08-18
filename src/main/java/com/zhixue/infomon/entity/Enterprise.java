package com.zhixue.infomon.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@DynamicUpdate
@DynamicInsert
@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    private String infoId;

    private String enterId;

    private String enterName;

    private String enterState;

    private String hostNum;

    private String defaultUser;

    private String linkNum;

    private String enterDay;

    private String enterAuth;

    private String innetIp;

    private String ipv4;

    private String state;

    private String signTime;

    private Date loginTime;



}
