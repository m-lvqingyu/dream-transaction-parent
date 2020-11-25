package com.dream.seata.order.server.client;


import com.dream.seata.order.server.client.fallback.UserAmountInfoFallbackFactory;
import com.dream.seata.user.api.UserAmountInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Lv.QingYu
 */
@FeignClient(value = "transaction-user-server", fallbackFactory = UserAmountInfoFallbackFactory.class)
public interface UserAmountInfoClient extends UserAmountInfoApi {
}
