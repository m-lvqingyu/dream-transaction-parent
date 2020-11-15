package com.dream.seata.user.server.controller;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.UserAmountInfoApi;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.server.service.UserAmountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@RestController
public class UserAmountInfoController implements UserAmountInfoApi {

    @Autowired
    private UserAmountInfoService userAmountInfoService;

    @Override
    public UserInfoAmountOutPut amountDetails(String userUid) {
        return userAmountInfoService.amountDetails(userUid);
    }

    @Override
    public Result settlement(String userUid, Integer version, BigDecimal deductionAmount) {
        return userAmountInfoService.settlement(userUid, version, deductionAmount);
    }
}
