package com.dream.seata.order.api;

import com.dream.seata.core.result.Result;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/06 22:37
 */

@RequestMapping(value = "/inward/order")
@FeignClient(value = "transaction-order-server")
public interface OrderInfoApi {

    /**
     * 根据订单ID，获取订单信息
     *
     * @param orderId
     * @return
     */
    @GetMapping("v1/getOrderDetails/{orderId}")
    OrderInfoOutPut getOrderInfo(@PathVariable("orderId") String orderId);

    /**
     * 创建订单，返回订单Id (分布式事务-XA模式)
     *
     * @param orderInfoInPut
     * @return
     */
    @PostMapping("v1/at/createOrder")
    Result createOrderInfoForAt(@RequestBody OrderInfoInPut orderInfoInPut);

    /**
     * 创建订单，返回订单Id (分布式事务-TCC模式)
     *
     * @param orderInfoInPut
     * @return
     */
    @PostMapping("v1/tcc/createOrder")
    Result createOrderInfoForTcc(@RequestBody OrderInfoInPut orderInfoInPut);
}
