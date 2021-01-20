package com.dev.testsos.sosgroup.service;

import com.dev.testsos.sosgroup.utils.MyPageUtil;
import com.dev.testsos.sosgroup.utils.ResultUtil;

public interface UserClientService {

    ResultUtil.ResultVO select(String clientId);
    MyPageUtil.PageInfo selectByDwellId(Integer dwellId,MyPageUtil.PageCond pageCond);
    boolean searchByIdCardNum(String idCardNum);
    ResultUtil.ResultVO checkIdCardNum(String idCardNum, String userName);
}
