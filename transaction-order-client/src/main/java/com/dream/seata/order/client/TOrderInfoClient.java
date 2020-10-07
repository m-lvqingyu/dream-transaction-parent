package com.dream.seata.order.client;

import com.dream.seata.order.api.TOrderInfoApi;
import com.dream.seata.order.input.OrderInfoInPut;
import com.dream.seata.order.output.OrderInfoOutPut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/06 22:47
 */

@FeignClient(value = "TRANSACTION-ORDER-SERVER", fallback = TOrderInfoClient.TOrderInfoFallback.class)
public interface TOrderInfoClient extends TOrderInfoApi {

    @Component
    class TOrderInfoFallback implements TOrderInfoClient {

        @Override
        public OrderInfoOutPut orderInfoByOrderId(String orderId) {
            return null;
        }

        @Override
        public String createOrderInfo(OrderInfoInPut orderInfoInPut) {
            return null;
        }
    }


}
