package com.dream.seata.user.server.service.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@LocalTCC
public interface UserAmountTccAction {

    /**
     * 第一阶段提交
     *
     * @param businessActionContext
     * @param userUid
     * @param version
     * @param deductionAmount
     * @return
     */
    @TwoPhaseBusinessAction(name = "userSettlement", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepareSettlement(BusinessActionContext businessActionContext,
                              @BusinessActionContextParameter(paramName = "userUid") String userUid,
                              @BusinessActionContextParameter(paramName = "version") Long version,
                              @BusinessActionContextParameter(paramName = "deductionAmount") BigDecimal deductionAmount);

    /**
     * 第二阶段提交
     *
     * @param businessActionContext
     * @return
     */
    boolean commit(BusinessActionContext businessActionContext);

    /**
     * 第二阶段回滚
     *
     * @param businessActionContext
     * @return
     */
    boolean rollback(BusinessActionContext businessActionContext);


}
