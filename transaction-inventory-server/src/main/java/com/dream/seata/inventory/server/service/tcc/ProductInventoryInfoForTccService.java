package com.dream.seata.inventory.server.service.tcc;

import com.dream.seata.core.result.Result;

/**
 * @author Lv.QingYu
 */
public interface ProductInventoryInfoForTccService {

    /**
     * 根据商品ID与版本号，扣减库存
     *
     * @param productUid 商品UID
     * @param productNum 扣减的库存数量
     * @param version    版本号
     * @return
     */
    Result reductionProductInventory(String productUid, Integer productNum, Long version);
}
