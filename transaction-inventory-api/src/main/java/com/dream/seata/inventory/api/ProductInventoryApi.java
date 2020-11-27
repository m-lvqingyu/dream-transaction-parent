package com.dream.seata.inventory.api;

import com.dream.seata.core.result.Result;
import com.dream.seata.inventory.api.fallback.ProductInventoryFallbackFactory;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lv.QingYu
 * @description FeignClient如果加了FallbackFactory，那业务出现异常的时候，进入降级逻辑了,异常会被上游服务捕获，此时，@GlobalTransactional会认为你的流程是正常的，就不会回滚了
 */
@RequestMapping("/inward/inventory")
@FeignClient(value = "transaction-inventory-server", fallbackFactory = ProductInventoryFallbackFactory.class)
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
                          @RequestParam("productNum") Long productNum,
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
                           @RequestParam("productNum") Long productNum,
                           @RequestParam("version") Long version);


}
