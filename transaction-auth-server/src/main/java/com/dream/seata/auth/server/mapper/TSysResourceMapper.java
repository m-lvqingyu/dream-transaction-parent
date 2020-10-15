package com.dream.seata.auth.server.mapper;

import com.dream.seata.auth.server.model.TSysResource;
import com.dream.seata.auth.server.model.TSysResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysResourceMapper {
    long countByExample(TSysResourceExample example);

    int deleteByExample(TSysResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysResource record);

    int insertSelective(TSysResource record);

    List<TSysResource> selectByExample(TSysResourceExample example);

    TSysResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysResource record, @Param("example") TSysResourceExample example);

    int updateByExample(@Param("record") TSysResource record, @Param("example") TSysResourceExample example);

    int updateByPrimaryKeySelective(TSysResource record);

    int updateByPrimaryKey(TSysResource record);
}