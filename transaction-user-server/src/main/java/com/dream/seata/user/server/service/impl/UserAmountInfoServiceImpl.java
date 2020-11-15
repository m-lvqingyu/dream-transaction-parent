package com.dream.seata.user.server.service.impl;

import com.dream.seata.core.exception.DreamCoreException;
import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.server.entity.UserAmountInfo;
import com.dream.seata.user.server.helper.UserAmountInfoHelper;
import com.dream.seata.user.server.service.UserAmountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Service
public class UserAmountInfoServiceImpl implements UserAmountInfoService {

    @Autowired
    private UserAmountInfoHelper userAmountInfoHelper;

    @Override
    public UserInfoAmountOutPut amountDetails(String userUid) {
        UserAmountInfo userAmountInfo = userAmountInfoHelper.getUserAmountInfo(userUid);
        if (userAmountInfo == null) {
            log.warn("[获取用户账户信息]-根据用户UID:{}未获取到用户账户信息", userUid);
            return null;
        }
        UserInfoAmountOutPut userInfoAmountOutPut = new UserInfoAmountOutPut();
        BeanUtils.copyProperties(userAmountInfo, userInfoAmountOutPut);
        return userInfoAmountOutPut;
    }

    @Override
    public Result settlement(String userUid, Integer version, BigDecimal deductionAmount) {
        UserAmountInfo userAmountInfo = userAmountInfoHelper.getUserAmountInfo(userUid);
        if (userAmountInfo == null) {
            log.warn("[扣减用户账户余额]-根据用户UID:{}未获取到用户账户信息", userUid);
            throw new DreamCoreException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        BigDecimal mainAmount = userAmountInfo.getMainAmount();
        if (mainAmount.compareTo(deductionAmount) < 0) {
            log.warn("[扣减用户账户余额]-用户账户余额不足，用户UID:{}", userUid);
            throw new DreamCoreException(ResultCode.USER_ACCOUNT_INSUFFICIENT_BALANCE);
        }
        BigDecimal amount = mainAmount.subtract(deductionAmount);
        UserAmountInfo updateUserAmountInfo = new UserAmountInfo();
        updateUserAmountInfo.setMainAmount(amount);
        updateUserAmountInfo.setVersion(version + 1);
        int result = userAmountInfoHelper.updateUserAmountInfo(userUid, version, updateUserAmountInfo);
        if (result <= 0) {
            throw new DreamCoreException(ResultCode.USER_ACCOUNT_UPDATE_ERROR);
        }
        return Result.success();
    }
}
