package com.dream.seata.order.server.controller.inward;

import com.dream.seata.core.result.Result;
import com.dream.seata.order.api.OrderInfoApi;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import com.dream.seata.order.server.service.at.OrderInfoForAtService;
import com.dream.seata.order.server.service.tcc.OrderInfoForTccService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lv.QingYu
 * @description 对于内部服务提供订单相关操作的接口（不需要经过网关鉴权）
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderInfoInwardController implements OrderInfoApi {

    private final OrderInfoForAtService orderInfoForAtService;

    private final OrderInfoForTccService orderInfoForTccService;

    @Override
    public OrderInfoOutPut getOrderInfo(String orderId) {
        return orderInfoForAtService.findOrderInfoByOrderId(orderId);
    }

    @Override
    public Result createOrderInfoForAt(OrderInfoInPut orderInfoInPut) {
        return orderInfoForAtService.createOrderInfo(orderInfoInPut);
    }

    @Override
    public Result createOrderInfoForTcc(OrderInfoInPut orderInfoInPut) {
        return orderInfoForTccService.createOrderInfo(orderInfoInPut);
    }
}
