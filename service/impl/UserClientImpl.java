package com.dev.testsos.sosgroup.service.impl;

import com.dev.testsos.sosgroup.mapper.OfficalMapper;
import com.dev.testsos.sosgroup.mapper.UserClientMapper;
import com.dev.testsos.sosgroup.po.OfficalPO;
import com.dev.testsos.sosgroup.po.UserClientPO;
import com.dev.testsos.sosgroup.service.UserClientService;
import com.dev.testsos.sosgroup.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserClientImpl implements UserClientService {
    @Autowired
    private UserClientMapper userClientMapper;
    @Autowired
    private OfficalMapper officalMapper;
    @Override
    public ResultUtil.ResultVO select(String clientId) {
        List<MyMapUtil.MyMap> clientIdNUM = officalMapper.getClientIdNUM(clientId);
        MyMapUtil.MyMap objectObjectHashMap = new MyMapUtil.MyMap();
        UserClientPO listClient = userClientMapper.findListClient(clientId);
        objectObjectHashMap.put("client",listClient);
        objectObjectHashMap.put("clientIdNum",clientIdNUM);
        return ResultUtil.success(objectObjectHashMap);
    }

    @Override
    public MyPageUtil.PageInfo selectByDwellId(Integer dwellId, MyPageUtil.PageCond pageCond) {
        Integer pageNum = pageCond.getPageNum();
        Integer pageSize = pageCond.getPageSize();
        List<MyMapUtil.MyMap> list = userClientMapper.selectList(dwellId,pageNum * pageSize,pageSize);
        Integer total =userClientMapper.selectdwellIdCount(dwellId);
        MyPageUtil.PageInfo pageInfo = MyPageUtil.PageInfo.getPageInfo(total, pageNum, pageSize);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public boolean searchByIdCardNum(String idCardNum) {
        idCardNum = idCardNum.trim();
        if(StringUtils.isBlank(idCardNum)){
            return true;
        }
        if(!(idCardNum.length() == IdCard.CHINA_ID_MIN_LENGTH || idCardNum.length() ==IdCard.CHINA_ID_MAX_LENGTH)){
            return true;
        }
        boolean flag = IdCard.isIdNum(idCardNum);
        if(!flag){
            return true;
        }
        UserClientPO userClientPO = userClientMapper.findByIdCard(idCardNum);
        if(userClientPO ==null){
            return false;
        }
        return true;
    }

    @Override
    public ResultUtil.ResultVO checkIdCardNum(String idCardNum, String userName) {
        IDCardUtil.IdCardCond idCardCond = new IDCardUtil.IdCardCond();
        idCardCond.setIdNo(idCardNum);
        idCardCond.setName(userName);
        Map map = new HashMap();
        //判断身份证是否大于15位数
        if (idCardCond.getIdNo().length() < 15) {
            return ResultUtil.custom(ResultUtil.ResultEnum.ERROR_IDCARD);
        }
        IDCardUtil.CardInfo cardInfo = null;
        try {
            cardInfo = IDCardUtil.idCardVerify(idCardCond);
        } catch (IDCardUtil.IdCardVerifyException e) {
            return ResultUtil.custom(ResultUtil.ResultEnum.OPT_FAIL);
        }

        if (cardInfo.getRespCode().equals("0000")) {
            map.put("result", true);
        } else if (cardInfo.getRespCode().equals("0002")) {
            return ResultUtil.custom(ResultUtil.ResultEnum.ERROR_IDNO_NAME_INFO);
        } else if (cardInfo.getRespCode().equals("0004")) {
            return ResultUtil.custom(ResultUtil.ResultEnum.ERROR_IDNO_NAME_INFO);
        } else if (cardInfo.getRespCode().equals("0007")) {
            return ResultUtil.custom(ResultUtil.ResultEnum.ERROR_IDNO_NAME_INFO);
        } else if (cardInfo.getRespCode().equals("0010")) {
            return ResultUtil.custom(ResultUtil.ResultEnum.ERROR_IDNO_SYSTEM);
        } else {
            map.put("result", false);
        }
        if (!(Boolean) map.get("result")) {
            return ResultUtil.custom(ResultUtil.ResultEnum.ERROR_IDNO_NAME_INFO);
        }
        return null;
    }
}
  