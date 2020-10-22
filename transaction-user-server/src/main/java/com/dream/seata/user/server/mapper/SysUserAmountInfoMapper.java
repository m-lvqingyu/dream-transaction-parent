package com.dream.seata.user.server.mapper;

import com.dream.seata.user.server.model.SysUserAmountInfo;
import com.dream.seata.user.server.model.SysUserAmountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserAmountInfoMapper {
    long countByExample(SysUserAmountInfoExample example);

    int deleteByExample(SysUserAmountInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserAmountInfo record);

    int insertSelective(SysUserAmountInfo record);

    List<SysUserAmountInfo> selectByExample(SysUserAmountInfoExample example);

    SysUserAmountInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserAmountInfo record, @Param("example") SysUserAmountInfoExample example);

    int updateByExample(@Param("record") SysUserAmountInfo record, @Param("example") SysUserAmountInfoExample example);

    int updateByPrimaryKeySelective(SysUserAmountInfo record);

    int updateByPrimaryKey(SysUserAmountInfo record);
}