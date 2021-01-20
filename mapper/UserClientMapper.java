package com.dev.testsos.sosgroup.mapper;

import com.dev.testsos.sosgroup.po.OfficalPO;
import com.dev.testsos.sosgroup.po.UserClientPO;
import com.dev.testsos.sosgroup.utils.MyMapUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface UserClientMapper extends Mapper<UserClientPO> {
    
    @Select("SELECT * FROM user_client WHERE client_id =#{clientId} LIMIT 1")
    UserClientPO findListClient(@Param("clientId")String clientId);
    @Select("SELECT client_id as fId, userName, idCardNum, date_format(createTime,'%Y-%m-%d %H:%i:%s') AS createTime,CONCAT('(',IF(sex =1 ,'男','女'),',',age,')') as info FROM user_client WHERE dwellId = #{dwellId} ORDER BY createTime LIMIT #{pageNum},#{pageSize}")
    List<MyMapUtil.MyMap> selectList(@Param("dwellId") Integer dwellId,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);
    @Select("SELECT COUNT(*) FROM user_client WHERE dwellId = #{dwellId}")
    Integer selectdwellIdCount(@Param("dwellId") Integer dwellId);
    @Select("SELECT * from user_client where idCardNum = #{idCardNum} limit 1")
    UserClientPO findByIdCard(String idCardNum);
}
