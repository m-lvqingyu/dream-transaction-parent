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

@RequestMapping("/order")
@FeignClient(value = "TRANSACTION-ORDER-SERVER")
public interface TOrderInfoApi {

    /**
     * 根据订单ID，获取订单信息
     *
     * @param orderId
     * @return
     */
    @GetMapping("info/{orderId}")
    OrderInfoOutPut orderInfoByOrderId(@PathVariable("orderId") String orderId);

    /**
     * 创建订单，返回订单Id
     *
     * @param orderInfoInPut
     * @return
     */
    @PostMapping("create")
    Result createOrderInfo(@RequestBody OrderInfoInPut orderInfoInPut);
}
