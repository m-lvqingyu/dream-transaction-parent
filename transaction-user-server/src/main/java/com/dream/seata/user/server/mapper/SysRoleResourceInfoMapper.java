package com.dream.seata.user.server.mapper;

import com.dream.seata.user.server.model.SysRoleResourceInfo;
import com.dream.seata.user.server.model.SysRoleResourceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleResourceInfoMapper {
    long countByExample(SysRoleResourceInfoExample example);

    int deleteByExample(SysRoleResourceInfoExample example);

    int insert(SysRoleResourceInfo record);

    int insertSelective(SysRoleResourceInfo record);

    List<SysRoleResourceInfo> selectByExample(SysRoleResourceInfoExample example);

    int updateByExampleSelective(@Param("record") SysRoleResourceInfo record, @Param("example") SysRoleResourceInfoExample example);

    int updateByExample(@Param("record") SysRoleResourceInfo record, @Param("example") SysRoleResourceInfoExample example);
}