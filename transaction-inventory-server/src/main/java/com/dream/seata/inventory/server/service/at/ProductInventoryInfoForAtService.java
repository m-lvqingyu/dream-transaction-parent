package com.dream.seata.inventory.server.service.at;

import com.dream.seata.core.result.Result;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;

/**
 * @author Lv.QingYu
 */
public interface ProductInventoryInfoForAtService {

    /**
     * 根据商品ID与版本号，扣减库存
     *
     * @param productUid 商品UID
     * @param productNum 扣减的库存数量
     * @param version    版本号
     * @return
     */
    Result reductionProductInventory(String productUid, Integer productNum, Long version);

    /**
     * 根据商品ID，获取商品库存信息
     *
     * @param productUid 商品ID
     * @return
     */
    ProductInventoryInfoOutPut findProductInventoryInfo(String productUid);

}
