package com.dev.testsos.sosgroup.controller;

import com.dev.testsos.sosgroup.service.UserClientService;
import com.dev.testsos.sosgroup.utils.IdCard;
import com.dev.testsos.sosgroup.utils.MyPageUtil;
import com.dev.testsos.sosgroup.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manage/vip/")
public class UserController {
    @Autowired
    private UserClientService userClientService;

    @GetMapping("clientId")
    ResultUtil.ResultVO clientId(String clientId) {
        return ResultUtil.success(userClientService.select(clientId));
    }
    @GetMapping("selectDwd")
    ResultUtil.ResultVO selectByDwellId(Integer dwellId,MyPageUtil.PageCond pageCond){
        return ResultUtil.success(userClientService.selectByDwellId(dwellId,pageCond));
    }
    @PostMapping("idCardNum/search")
    public ResultUtil.ResultVO searchByIdCardNum(@RequestParam("idCardNum") String idCardNum, @RequestParam("userName") String userName) {
        ResultUtil.ResultVO resultVO = userClientService.checkIdCardNum(idCardNum, userName);
        if(null != resultVO){
            return resultVO;
        }
        boolean byIdCardNum = userClientService.searchByIdCardNum(idCardNum);
        Map map = new HashMap();
        map.put("ifExit",byIdCardNum);
        if(byIdCardNum == false){
            Integer sex = Integer.parseInt(IdCard.getGenderByIdCard(idCardNum));
            Integer age = IdCard.getAgeByIdCard(idCardNum.trim());
            String birthday = IdCard.getBirthByIdCard(idCardNum);
            map.put("sex",sex);
            map.put("age",age);
            map.put("birthday",birthday);
        }
        return ResultUtil.success(map);
    }
    
}
