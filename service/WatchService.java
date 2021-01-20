package com.dev.testsos.sosgroup.service;

import com.dev.testsos.sosgroup.dto.WatchDTO;
import com.dev.testsos.sosgroup.utils.ResultUtil;

import java.util.Map;

public interface WatchService {
    Map list(Integer pageNum, Integer pageSize, String deviceId, Integer typeId, String mac);
    
    ResultUtil.ResultVO add(WatchDTO watchDTO,String deviceId);
}
