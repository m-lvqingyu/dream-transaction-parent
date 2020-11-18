package com.dream.seata.order.server.service.tcc;

import com.dream.seata.core.result.Result;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;

/**
 * @author Lv.QingYu
 */
public interface OrderInfoForTccService {

    /**
     * 创建订单
     *
     * @param orderInfoInPut
     * @return
     */
    Result createOrderInfo(OrderInfoInPut orderInfoInPut);


}
