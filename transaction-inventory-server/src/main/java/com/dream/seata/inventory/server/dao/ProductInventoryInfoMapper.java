package com.dream.seata.inventory.server.dao;

import com.dream.seata.inventory.server.entity.ProductInventoryInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Lv.QingYu
 */
public interface ProductInventoryInfoMapper {

    /**
     * 根据商品UID与版本号，获取商品库存信息
     *
     * @param productUid 商品Uid
     * @param version    版本号
     * @return
     */
    ProductInventoryInfo selectByUidAndVs(@Param("productUid") String productUid, @Param("version") Long version);

    /**
     * 扣减商品库存
     *
     * @param productUid 商品Uid
     * @param version    版本号
     * @param num        数量
     * @return
     */
    int updateStock(@Param("productUid") String productUid, @Param("version") Long version, @Param("num") Long num);

    /**
     * 扣减商品库存-TCC模式一阶段提交
     *
     * @param productUid 商品唯一ID
     * @param version    版本号
     * @param num        数量
     * @return
     */
    int TX1StockSubmit(@Param("productUid") String productUid, @Param("version") long version, @Param("num") Long num);

    /**
     * 扣减商品库存-TCC模式二阶段提交
     *
     * @param productUid 商品唯一ID
     * @param num        数量
     * @return
     */
    int TX2StockSubmit(@Param("productUid") String productUid, @Param("num") Long num);

    /**
     * 扣减商品库存-TCC模式二阶段回滚
     *
     * @param productUid 商品唯一ID
     * @param num        数量
     * @return
     */
    int TX2StockFallBack(@Param("productUid") String productUid, @Param("num") Long num);
}
