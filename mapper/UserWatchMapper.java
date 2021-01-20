package com.dev.testsos.sosgroup.mapper;

import com.dev.testsos.sosgroup.dto.WatchDTO;
import com.dev.testsos.sosgroup.po.UseWatchPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface UserWatchMapper extends Mapper<UseWatchPO> {
    @Select("SELECT wt.deviceId as 'deviceId',IFNULL(heart,0) AS 'heart',IFNULL(hp,0) AS 'hp',IFNULL(lp,0) AS 'lp' FROM watch_type wt WHERE wt.deviceId IS NOT null AND wt.deviceId like CONCAT('%',deviceId,'%') LIMIT #{pageNum},#{pageSize}")
    List<WatchDTO> list(@Param("deviceId") String deviceId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    @Select("SELECT COUNT(deviceId) FROM watch_type WHERE deviceId LIKE CONCAT('%',#{deviceId},'%')")
    Integer listCount(@Param("deviceId") String deviceId);
    @Select("SELECT * FROM watch_type WHERE deviceId = #{deviceId} ORDER BY id DESC LIMIT 1")
    UseWatchPO getWatch(@Param("deviceId") String deviceId);
    @Select("SELECT typeId FROM watch_type WHERE deviceId = #{deviceId}")
    Integer getWatchId(@Param("deviceId")String deviceId);
    
}
