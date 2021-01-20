package com.dev.testsos.sosgroup.po;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "living_med")
public class OfficalPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "tel")
    private String  tel;
    @Column(name = "name")
    private String name;
    @Column(name = "relationship")
    private String relationship;
    @Column(name = "client_id")
    private Integer clientId;
    
}
