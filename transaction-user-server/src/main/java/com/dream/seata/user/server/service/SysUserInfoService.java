package com.dream.seata.user.server.service;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.api.output.UserInfoOutPut;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
public interface SysUserInfoService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户姓名
     * @return
     */
    UserInfoOutPut loadUserByUsername(String username);

    /**
     * 根据用户ID，获取当前登录用户信息
     *
     * @param userUid 用户UID
     * @return
     */
    UserInfoOutPut loginUserInfoDetails(String userUid);

    /**
     * 根据UID，获取用户账户信息
     *
     * @param userUid 用户UId
     * @return
     */
    UserInfoAmountOutPut userInfoAmountDetailsByUid(String userUid);

    /**
     * 订单结算，扣减账户金额
     *
     * @param userUid
     * @param deductionAmount
     * @return
     */
    Result userInfoSettlement(String userUid, BigDecimal deductionAmount);
}
