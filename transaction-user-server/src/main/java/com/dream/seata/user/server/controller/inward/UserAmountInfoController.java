package com.dream.seata.user.server.controller.inward;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.UserAmountInfoApi;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.server.service.at.UserAmountInfoForAtService;
import com.dream.seata.user.server.service.tcc.UserAmountInfoForTccService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAmountInfoController implements UserAmountInfoApi {

    private final UserAmountInfoForAtService userAmountInfoForAtService;

    private final UserAmountInfoForTccService userAmountInfoForTccService;

    @Override
    public UserInfoAmountOutPut amountDetails(String userUid) {
        return userAmountInfoForAtService.amountDetails(userUid);
    }

    @Override
    public Result settlementForAt(String userUid, Integer version, BigDecimal deductionAmount) {
        return userAmountInfoForAtService.settlement(userUid, version, deductionAmount);
    }

    @Override
    public Result settlementForTcc(String userUid, Integer version, BigDecimal deductionAmount) {
        return userAmountInfoForTccService.settlement(userUid, version, deductionAmount);
    }

}
