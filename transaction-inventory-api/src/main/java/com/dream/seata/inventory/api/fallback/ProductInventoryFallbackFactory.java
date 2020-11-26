package com.dream.seata.inventory.api.fallback;

import com.dream.seata.core.result.Result;
import com.dream.seata.inventory.api.ProductInventoryApi;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import com.google.common.base.Throwables;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
public class ProductInventoryFallbackFactory implements FallbackFactory<ProductInventoryApi> {
    @Override
    public ProductInventoryApi create(Throwable throwable) {
        return new ProductInventoryApi() {
            @Override
            public ProductInventoryInfoOutPut getInventoryDetails(String productUid) {
                log.error("[服务降级]-[获取商品库存详情接口]-productUid:{},ex:{}", productUid, Throwables.getStackTraceAsString(throwable));
                return null;
            }

            @Override
            public Result reductionForAt(String productUid, Integer productNum, Long version) {
                log.error("[服务降级]-[AT模式]-[扣减库存接口]-productUid:{},ex:{}", productUid, Throwables.getStackTraceAsString(throwable));
                return null;
            }

            @Override
            public Result reductionForTcc(String productUid, Integer productNum, Long version) {
                log.error("[服务降级]-[TCC模式]-[扣减库存接口]-productUid:{},ex:{}", productUid, Throwables.getStackTraceAsString(throwable));
                return null;
            }
        };
    }
}
