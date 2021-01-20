package com.dev.testsos.sosgroup.controller;

import com.dev.testsos.sosgroup.dto.WatchDTO;
import com.dev.testsos.sosgroup.service.WatchService;
import com.dev.testsos.sosgroup.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage/watch/")
public class WatchController {
    @Autowired
    private WatchService watchService;
    @GetMapping("list")
    ResultUtil.ResultVO list(Integer pageNum, Integer pageSize, String deviceId, Integer typeId, String mac) {
        return ResultUtil.success(watchService.list(pageNum,pageSize,deviceId,typeId,mac));
    }
    @PostMapping("add")
    ResultUtil.ResultVO add(WatchDTO watchDTO, @RequestAttribute("deviceId")String deviceId){
        return watchService.add(watchDTO,deviceId);
    }
}
