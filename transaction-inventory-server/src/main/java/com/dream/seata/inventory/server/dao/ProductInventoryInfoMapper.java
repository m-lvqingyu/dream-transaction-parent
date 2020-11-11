package com.dream.seata.inventory.server.dao;

import com.dream.seata.inventory.server.entity.ProductInventoryInfo;
import com.dream.seata.inventory.server.entity.ProductInventoryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductInventoryInfoMapper {
    long countByExample(ProductInventoryInfoExample example);

    int deleteByExample(ProductInventoryInfoExample example);

    int deleteByPrimaryKey(Long inventoryId);

    int insert(ProductInventoryInfo record);

    int insertSelective(ProductInventoryInfo record);

    List<ProductInventoryInfo> selectByExample(ProductInventoryInfoExample example);

    ProductInventoryInfo selectByPrimaryKey(Long inventoryId);

    int updateByExampleSelective(@Param("record") ProductInventoryInfo record, @Param("example") ProductInventoryInfoExample example);

    int updateByExample(@Param("record") ProductInventoryInfo record, @Param("example") ProductInventoryInfoExample example);

    int updateByPrimaryKeySelective(ProductInventoryInfo record);

    int updateByPrimaryKey(ProductInventoryInfo record);
}