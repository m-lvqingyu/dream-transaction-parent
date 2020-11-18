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
@RequestMapping("/inward/inventory")
@FeignClient(value = "transaction-inventory-server")
public interface ProductInventoryApi {

    /**
     * 根据商品ID，获取商品库存信息
     *
     * @param productUid 商品ID
     * @return
     */
    @PostMapping("v1/getInventoryDetails")
    ProductInventoryInfoOutPut getInventoryDetails(@RequestParam("productUid") String productUid);

    /**
     * 扣减商品库存
     *
     * @param productUid 商品唯一ID
     * @param productNum 商品数量
     * @param version    版本号
     * @return
     */
    @PostMapping("v1/reductionForAt")
    Result reductionForAt(@RequestParam("productUid") String productUid,
                          @RequestParam("productNum") Integer productNum,
                          @RequestParam("version") Long version);

    /**
     * 扣减商品库存
     *
     * @param productUid 商品唯一ID
     * @param productNum 商品数量
     * @param version    版本号
     * @return
     */
    @PostMapping("v1/reductionForTcc")
    Result reductionForTcc(@RequestParam("productUid") String productUid,
                           @RequestParam("productNum") Integer productNum,
                           @RequestParam("version") Long version);


}
