package com.dream.seata.order.server.controller.outward;

import com.dream.seata.core.result.Result;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import com.dream.seata.order.server.service.at.OrderInfoForAtService;
import com.dream.seata.order.server.service.tcc.OrderInfoForTccService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lv.QingYu
 * @description 对于外部服务提供订单相关操作的接口（需要走网关鉴权）
 */
@RestController
@RequestMapping(value = "/outward/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderInfoOutwardController {

    private final OrderInfoForAtService orderInfoForAtService;

    private final OrderInfoForTccService orderInfoForTccService;

    @GetMapping(value = "getOrderInfo/{orderId}")
    public OrderInfoOutPut getOrderInfo(@PathVariable("orderId") String orderId) {
        return orderInfoForAtService.findOrderInfoByOrderId(orderId);
    }

    @PostMapping(value = "createOrderInfoForAt")
    public Result createOrderInfoForAt(@RequestBody OrderInfoInPut orderInfoInPut) throws InterruptedException {
        Integer productNum = orderInfoInPut.getProductNum();
        if(productNum != 1){
            Thread.sleep(productNum * 1000);
        }
        return orderInfoForAtService.createOrderInfo(orderInfoInPut);
    }

    @PostMapping(value = "createOrderInfoForTcc")
    public Result createOrderInfoForTcc(@RequestBody OrderInfoInPut orderInfoInPut) {
        return orderInfoForTccService.createOrderInfo(orderInfoInPut);
    }

}
