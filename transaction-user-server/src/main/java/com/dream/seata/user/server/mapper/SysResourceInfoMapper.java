package com.dream.seata.user.server.mapper;

import com.dream.seata.user.server.model.SysResourceInfo;
import com.dream.seata.user.server.model.SysResourceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysResourceInfoMapper {
    long countByExample(SysResourceInfoExample example);

    int deleteByExample(SysResourceInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysResourceInfo record);

    int insertSelective(SysResourceInfo record);

    List<SysResourceInfo> selectByExample(SysResourceInfoExample example);

    SysResourceInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysResourceInfo record, @Param("example") SysResourceInfoExample example);

    int updateByExample(@Param("record") SysResourceInfo record, @Param("example") SysResourceInfoExample example);

    int updateByPrimaryKeySelective(SysResourceInfo record);

    int updateByPrimaryKey(SysResourceInfo record);
}