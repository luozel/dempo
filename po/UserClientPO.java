package com.dev.testsos.sosgroup.po;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user_client")
public class UserClientPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "nation")
    private String nation;
    @Column(name = "age")
    private Integer age;
    @Column(name = "idCardNum")
    private String idCardNum;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "primaryContactNumber")
    private String primaryContactNumber;
    @Column(name = "language")
    private String language;
    @Column(name = "education")
    private String education;
    @Column(name = "contactNumber")
    private String contactNumber;
    @Column(name = "incomeSource")
    private String incomeSource;
    @Column(name = "clientDeviceType")
    private String clientDeviceType;
    @Column(name = "dwell")
    private String dwell;
    @Column(name = "dwellId")
    private Integer dwellId;
    @Column(name = "address")
    private String address;
    @Column(name = "livSituation")
    private String livSituation;
    @Column(name = "bodyStatus")
    private String bodyStatus;
    @Column(name = "medicalHistory")
    private String medicalHistory;
    @Column(name="serviceAppeal")
    private String serviceAppeal;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "updatetime")
    private Date  updatetime;
}
