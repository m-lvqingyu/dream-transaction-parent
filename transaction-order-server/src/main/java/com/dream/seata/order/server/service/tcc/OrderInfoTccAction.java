package com.dream.seata.order.server.service.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author Lv.QingYu
 */
@LocalTCC
public interface OrderInfoTccAction {

    /**
     * 第一阶段
     *
     * @param businessActionContext
     * @param userUid
     * @param recipientUid
     * @param productUid
     * @return
     */
    @TwoPhaseBusinessAction(name = "orderTccAction", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepareCreateOrder(BusinessActionContext businessActionContext,
                               @BusinessActionContextParameter(paramName = "userUid") String userUid,
                               @BusinessActionContextParameter(paramName = "recipientUid") String recipientUid,
                               @BusinessActionContextParameter(paramName = "productUid") String productUid);

    /**
     * 第二阶段 - 提交
     *
     * @param businessActionContext
     * @return
     */
    boolean commit(BusinessActionContext businessActionContext);

    /**
     * 第二阶段 - 回滚
     *
     * @param businessActionContext
     * @return
     */
    boolean rollback(BusinessActionContext businessActionContext);

}
