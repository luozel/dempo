package com.dev.testsos.sosgroup.dto;


import lombok.Data;

@Data
public class WatchDTO {
    private String  deviceId="";
    private String  mac="";
    private Integer heart = 0;
    private Integer ox = 0;
    private Integer hp = 0;
    private Integer lp = 0;
    private Integer typeId = 2;
}
