package com.dream.seata.order.server.service;

import com.dream.seata.core.result.Result;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/06 22:35
 */
public interface TOrderInfoService {

    /**
     * 根据订单编号获取订单信息
     *
     * @param orderId
     * @return
     */
    OrderInfoOutPut findOrderInfoByOrderId(String orderId);

    /**
     * 创建订单
     *
     * @param orderInfoInPut
     * @return
     */
    Result createOrderInfo(OrderInfoInPut orderInfoInPut);


}
