package com.dev.testsos.sosgroup.mapper;

import com.dev.testsos.sosgroup.po.OfficalPO;
import com.dev.testsos.sosgroup.utils.MyMapUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface OfficalMapper extends Mapper<OfficalPO> {
    @Select("SELECT * FROM living_med WHERE client_id =#{clientId}")
    List<MyMapUtil.MyMap> getClientIdNUM(@Param("clientId")String clientId);
}
