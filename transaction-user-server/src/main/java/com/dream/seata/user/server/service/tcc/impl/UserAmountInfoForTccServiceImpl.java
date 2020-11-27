package com.dream.seata.user.server.service.tcc.impl;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.server.service.tcc.UserAmountInfoForTccService;
import com.dream.seata.user.server.service.tcc.UserAmountTccAction;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAmountInfoForTccServiceImpl implements UserAmountInfoForTccService {

    private final UserAmountTccAction userAmountTccAction;

    @GlobalTransactional
    @Override
    public Result settlement(String userUid, Long version, BigDecimal deductionAmount) {
        if(deductionAmount.compareTo(new BigDecimal(20)) == 0){
            throw new RuntimeException("分布式事务Tcc模式测试");
        }
        userAmountTccAction.prepareSettlement(null, userUid, version, deductionAmount);
        if(deductionAmount.compareTo(new BigDecimal(30)) == 0){
            throw new RuntimeException("分布式事务Tcc模式测试");
        }
        return Result.success();
    }
}
