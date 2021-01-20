package com.dev.testsos.sosgroup.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "watch_type")
public class UseWatchPO {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "deviceId")
    private Integer deviceId;
    @Column(name = "mac")
    private String mac;
    @Column(name = "typeId")
    private Integer typeId;
    @Column(name = "heart")
    private String heart;
    @Column(name = "ox")
    private String ox;
    @Column(name = "hp")
    private String hp;
    @Column(name = "lp")
    private String lp;
}
