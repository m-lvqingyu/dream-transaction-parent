package com.dream.seata.order.server.controller;

import com.dream.seata.core.result.Result;
import com.dream.seata.order.api.OrderInfoApi;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import com.dream.seata.order.server.service.OrderInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: <pre>
 * </pre>
 * @author: Lv.QingYu
 * @create: 2020/10/06 22:52
 */

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderInfoController implements OrderInfoApi {

    private final OrderInfoService tOrderInfoService;

    @Override
    public OrderInfoOutPut getOrderInfo(String orderId) {
        return tOrderInfoService.findOrderInfoByOrderId(orderId);
    }

    @Override
    public Result createOrderInfo(OrderInfoInPut orderInfoInPut) {
        return tOrderInfoService.createOrderInfo(orderInfoInPut);
    }
}
