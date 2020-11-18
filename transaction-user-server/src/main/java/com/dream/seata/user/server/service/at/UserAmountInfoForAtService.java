package com.dream.seata.user.server.service.at;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
public interface UserAmountInfoForAtService {

    /**
     * 根据UID，获取用户账户信息
     *
     * @param userUid 用户UId
     * @return
     */
    UserInfoAmountOutPut amountDetails(String userUid);

    /**
     * 订单结算，扣减账户金额
     *
     * @param userUid         用户唯一ID
     * @param version         版本号
     * @param deductionAmount 扣减金额
     * @return
     */
    Result settlement(String userUid, Integer version, BigDecimal deductionAmount);
}
