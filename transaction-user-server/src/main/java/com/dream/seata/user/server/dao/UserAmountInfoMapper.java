package com.dream.seata.user.server.dao;

import com.dream.seata.user.server.entity.UserAmountInfo;
import com.dream.seata.user.server.entity.UserAmountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAmountInfoMapper {
    long countByExample(UserAmountInfoExample example);

    int deleteByExample(UserAmountInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAmountInfo record);

    int insertSelective(UserAmountInfo record);

    List<UserAmountInfo> selectByExample(UserAmountInfoExample example);

    UserAmountInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAmountInfo record, @Param("example") UserAmountInfoExample example);

    int updateByExample(@Param("record") UserAmountInfo record, @Param("example") UserAmountInfoExample example);

    int updateByPrimaryKeySelective(UserAmountInfo record);

    int updateByPrimaryKey(UserAmountInfo record);
}