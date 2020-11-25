package com.dream.seata.order.server.client.fallback;

import com.dream.seata.core.result.Result;
import com.dream.seata.order.server.client.UserAmountInfoClient;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
public class UserAmountInfoFallbackFactory implements FallbackFactory<UserAmountInfoClient> {

    @Override
    public UserAmountInfoClient create(Throwable throwable) {
        return new UserAmountInfoClient() {
            @Override
            public UserInfoAmountOutPut amountDetails(String userUid) {
                log.error("[服务降级]-[获取用户信息接口]-UserUid:{}", userUid);
                return null;
            }

            @Override
            public Result settlementForAt(String userUid, Integer version, BigDecimal deductionAmount) {
                log.error("[服务降级]-[扣款接口]-[AT模式]-UserUid:{}", userUid);
                return null;
            }

            @Override
            public Result settlementForTcc(String userUid, Integer version, BigDecimal deductionAmount) {
                log.error("[服务降级]-[扣款接口]-[TCC模式]-UserUid:{}", userUid);
                return null;
            }
        };
    }
}
