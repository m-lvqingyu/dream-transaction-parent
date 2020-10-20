package com.dream.seata.user.server.mapper;

import com.dream.seata.user.server.model.SysUserRoleInfo;
import com.dream.seata.user.server.model.SysUserRoleInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleInfoMapper {
    long countByExample(SysUserRoleInfoExample example);

    int deleteByExample(SysUserRoleInfoExample example);

    int deleteByPrimaryKey(SysUserRoleInfo key);

    int insert(SysUserRoleInfo record);

    int insertSelective(SysUserRoleInfo record);

    List<SysUserRoleInfo> selectByExample(SysUserRoleInfoExample example);

    int updateByExampleSelective(@Param("record") SysUserRoleInfo record, @Param("example") SysUserRoleInfoExample example);

    int updateByExample(@Param("record") SysUserRoleInfo record, @Param("example") SysUserRoleInfoExample example);
}
