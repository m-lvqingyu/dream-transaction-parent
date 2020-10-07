package com.dream.seata.order.server.mapper;

import com.dream.seata.order.server.model.TOrderInfo;
import com.dream.seata.order.server.model.TOrderInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOrderInfoMapper {
    long countByExample(TOrderInfoExample example);

    int deleteByExample(TOrderInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TOrderInfo record);

    int insertSelective(TOrderInfo record);

    List<TOrderInfo> selectByExample(TOrderInfoExample example);

    TOrderInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TOrderInfo record, @Param("example") TOrderInfoExample example);

    int updateByExample(@Param("record") TOrderInfo record, @Param("example") TOrderInfoExample example);

    int updateByPrimaryKeySelective(TOrderInfo record);

    int updateByPrimaryKey(TOrderInfo record);
}
