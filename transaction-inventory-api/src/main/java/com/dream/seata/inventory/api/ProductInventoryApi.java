package com.dream.seata.inventory.api;

import com.dream.seata.core.result.Result;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lv.QingYu
 */
@RequestMapping("/inventory")
@FeignClient(value = "transaction-inventory-server")
public interface ProductInventoryApi {

    /**
     * 扣减商品库存
     *
     * @param productUid 商品唯一ID
     * @param productNum 商品数量
     * @param version    版本号
     * @return
     */
    @PostMapping("reduction")
    Result reductionProductInventory(@RequestParam("productUid") String productUid,
                                     @RequestParam("productNum") Integer productNum,
                                     @RequestParam("version") Long version);

    /**
     * 根据商品ID，获取商品库存信息
     *
     * @param productUid 商品ID
     * @return
     */
    @PostMapping("findProductInventoryInfo")
    ProductInventoryInfoOutPut findProductInventoryInfo(@RequestParam("productUid") String productUid);


}
