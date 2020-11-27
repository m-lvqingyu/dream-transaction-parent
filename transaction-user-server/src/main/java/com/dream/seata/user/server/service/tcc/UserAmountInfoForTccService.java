package com.dream.seata.user.server.service.tcc;

import com.dream.seata.core.result.Result;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
public interface UserAmountInfoForTccService {

    /**
     * 订单结算，扣减账户金额
     *
     * @param userUid         用户唯一ID
     * @param version         版本号
     * @param deductionAmount 扣减金额
     * @return
     */
    Result settlement(String userUid, Long version, BigDecimal deductionAmount);
}
