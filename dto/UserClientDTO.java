package com.dev.testsos.sosgroup.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class UserClientDTO {

    private Integer clientId;
    private String userName;
    private Integer sex;
    private String nation;
    private Integer age;
    private String idCardNum;
    private String birthday;
    private String primaryContactNumber;
    private String language;
    private String education;
    private String contactNumber;
    private String incomeSource;
    private String clientDeviceType;
    private String dwell;
    private Integer dwellId;
    private String address;
    private List<OfficalDTO> streetManager;
    private List<OfficalDTO> emergencyContact;
    private String livSituation;
    private String bodyStatus;
    private String medicalHistory;
    private String serviceAppeal;
    private Date createTime;
    private Date updatetime;
}
