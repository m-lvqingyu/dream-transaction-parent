package com.dream.seata.order.api;

import com.dream.seata.order.input.OrderInfoInPut;
import com.dream.seata.order.output.OrderInfoOutPut;
import org.springframework.web.bind.annotation.*;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/06 22:37
 */

@RequestMapping("/order")
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
    String createOrderInfo(@RequestBody OrderInfoInPut orderInfoInPut);
}
