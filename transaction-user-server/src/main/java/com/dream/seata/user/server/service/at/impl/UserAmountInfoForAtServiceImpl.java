package com.dream.seata.user.server.service.at.impl;

import com.dream.seata.core.exception.DreamCoreException;
import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.server.entity.UserAmountInfo;
import com.dream.seata.user.server.helper.UserAmountInfoHelper;
import com.dream.seata.user.server.service.at.UserAmountInfoForAtService;
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
public class UserAmountInfoForAtServiceImpl implements UserAmountInfoForAtService {

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
        int result = userAmountInfoHelper.settlement(userUid, deductionAmount, version);
        if (result <= 0) {
            throw new DreamCoreException(ResultCode.USER_ACCOUNT_UPDATE_ERROR);
        }
        return Result.success();
    }
}
