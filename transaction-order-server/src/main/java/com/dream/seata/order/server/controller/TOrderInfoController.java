package com.dream.seata.order.server.controller;

import com.dream.seata.order.api.TOrderInfoApi;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import com.dream.seata.order.server.service.TOrderInfoService;
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
public class TOrderInfoController implements TOrderInfoApi {

    private final TOrderInfoService tOrderInfoService;

    @Override
    public OrderInfoOutPut orderInfoByOrderId(String orderId) {
        return tOrderInfoService.findOrderInfoByOrderId(orderId);
    }

    @Override
    public String createOrderInfo(OrderInfoInPut orderInfoInPut) {
        return tOrderInfoService.createOrderInfo(orderInfoInPut);
    }
}
