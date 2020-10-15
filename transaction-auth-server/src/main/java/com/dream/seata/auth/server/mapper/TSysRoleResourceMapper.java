package com.dream.seata.auth.server.mapper;

import com.dream.seata.auth.server.model.TSysRoleResource;
import com.dream.seata.auth.server.model.TSysRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysRoleResourceMapper {
    long countByExample(TSysRoleResourceExample example);

    int deleteByExample(TSysRoleResourceExample example);

    int insert(TSysRoleResource record);

    int insertSelective(TSysRoleResource record);

    List<TSysRoleResource> selectByExample(TSysRoleResourceExample example);

    int updateByExampleSelective(@Param("record") TSysRoleResource record, @Param("example") TSysRoleResourceExample example);

    int updateByExample(@Param("record") TSysRoleResource record, @Param("example") TSysRoleResourceExample example);
}