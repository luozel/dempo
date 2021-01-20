package com.dev.testsos.sosgroup.service.impl;

import com.dev.testsos.sosgroup.dto.WatchDTO;
import com.dev.testsos.sosgroup.mapper.UserWatchMapper;
import com.dev.testsos.sosgroup.po.UseWatchPO;
import com.dev.testsos.sosgroup.service.WatchService;
import com.dev.testsos.sosgroup.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WatchServiceImpl implements WatchService {
    @Autowired
    private UserWatchMapper userWatchMapper;

    @Override
    public Map list(Integer pageNum, Integer pageSize, String deviceId, Integer typeId, String mac) {
        if (StringUtils.isBlank(mac)){
            mac = "";
        }
        if (StringUtils.isBlank(deviceId)){
            deviceId = "";
        }
        Integer total = 0;
        List<WatchDTO> list = new ArrayList<>();
        total = userWatchMapper.listCount(deviceId);
        list = userWatchMapper.list(deviceId,(pageNum-1)*pageSize,pageSize);
        Integer pages = (total+pageSize-1)/pageSize;
        Map map = new HashMap();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("pages",pages);
        map.put("total",total);
        map.put("list",list);
        return map;
    }

    @Override
    public ResultUtil.ResultVO add(WatchDTO watchDTO, String deviceId) {
        Integer watchId = userWatchMapper.getWatchId(deviceId);
        
        return ResultUtil.success();
    }
}
