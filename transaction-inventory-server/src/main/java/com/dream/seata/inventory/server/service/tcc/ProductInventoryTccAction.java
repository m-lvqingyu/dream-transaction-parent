package com.dream.seata.inventory.server.service.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author Lv.QingYu
 */
@LocalTCC
public interface ProductInventoryTccAction {

    /**
     * 一阶段提交
     *
     * @param businessActionContext
     * @param productUid
     * @param productNum
     * @param version
     * @return
     */
    @TwoPhaseBusinessAction(name = "reductionProductInventory", rollbackMethod = "rollback", commitMethod = "commit")
    boolean prepareReductionProductInventory(BusinessActionContext businessActionContext,
                                             @BusinessActionContextParameter(paramName = "productUid") String productUid,
                                             @BusinessActionContextParameter(paramName = "productNum") Integer productNum,
                                             @BusinessActionContextParameter(paramName = "version") Long version);

    /**
     * 二阶段提交
     *
     * @param businessActionContext
     * @return
     */
    boolean commit(BusinessActionContext businessActionContext);

    /**
     * 二阶段回滚
     *
     * @param businessActionContext
     * @return
     */
    boolean rollback(BusinessActionContext businessActionContext);
}
