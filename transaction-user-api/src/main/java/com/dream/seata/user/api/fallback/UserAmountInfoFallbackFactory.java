package com.dream.seata.user.api.fallback;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.UserAmountInfoApi;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.google.common.base.Throwables;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
public class UserAmountInfoFallbackFactory implements FallbackFactory<UserAmountInfoApi> {

    @Override
    public UserAmountInfoApi create(Throwable throwable) {
        return new UserAmountInfoApi() {
            @Override
            public UserInfoAmountOutPut amountDetails(String userUid) {
                log.error("[服务降级]-[获取用户信息接口]-UserUid:{},ex:{}", userUid, Throwables.getStackTraceAsString(throwable));
                return null;
            }

            @Override
            public Result settlementForAt(String userUid, Integer version, BigDecimal deductionAmount) {
                log.error("[服务降级]-[扣款接口]-[AT模式]-UserUid:{},ex:{}", userUid, Throwables.getStackTraceAsString(throwable));
                return null;
            }

            @Override
            public Result settlementForTcc(String userUid, Integer version, BigDecimal deductionAmount) {
                log.error("[服务降级]-[扣款接口]-[TCC模式]-UserUid:{},ex:{}", userUid, Throwables.getStackTraceAsString(throwable));
                return null;
            }
        };
    }
}
