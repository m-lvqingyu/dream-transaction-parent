package com.dream.seata.order.server.service.tcc.impl;

import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.inventory.api.ProductInventoryApi;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.server.service.tcc.OrderInfoForTccService;
import com.dream.seata.order.server.service.tcc.OrderInfoTccAction;
import com.dream.seata.user.api.UserAmountInfoApi;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderInfoForTccServiceImpl implements OrderInfoForTccService {

    private final OrderInfoTccAction orderInfoTccAction;

    private final ProductInventoryApi productInventoryApi;

    private final UserAmountInfoApi userAmountInfoApi;

    @GlobalTransactional
    @Override
    public Result createOrderInfo(OrderInfoInPut orderInfoInPut) {
        String productUid = orderInfoInPut.getProductUid();
        ProductInventoryInfoOutPut inventoryDetails = productInventoryApi.getInventoryDetails(productUid);
        if (inventoryDetails == null) {
            log.info("[分布式事务]-[创建订单]-根据商品ID:{}未获取到商品库存信息", productUid);
            return Result.custom(ResultCode.PRODUCT_INVENTORY_NOT_EXIST_ERROR);
        }
        int productNum = orderInfoInPut.getProductNum();
        long currentInventoryNum = inventoryDetails.getCurrentInventoryNum();
        if (productNum > currentInventoryNum) {
            log.info("[分布式事务]-[创建订单]-商品ID:{}，库存不足", productUid);
            return Result.custom(ResultCode.PRODUCT_INVENTORY_NOT_ENOUGH_ERROR);
        }
        String userUid = orderInfoInPut.getUserUid();
        UserInfoAmountOutPut userInfoAmountOutPut = userAmountInfoApi.amountDetails(userUid);
        if (userInfoAmountOutPut == null) {
            log.info("[分布式事务]-[创建订单]-根据用户ID:{}，未获取到用户账户信息", userUid);
            return Result.custom(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        BigDecimal mainAmount = userInfoAmountOutPut.getMainAmount();
        BigDecimal productAmount = new BigDecimal(productNum * 10.00);
        if (mainAmount.compareTo(productAmount) < 0) {
            log.info("[分布式事务]-[创建订单]-用户ID:{}，用户的账户余额不足", userUid);
            return Result.custom(ResultCode.USER_ACCOUNT_INSUFFICIENT_BALANCE);
        }
        // 创建订单
        String recipientUid = orderInfoInPut.getRecipientUid();
        orderInfoTccAction.prepareCreateOrder(null, userUid, recipientUid, productUid);
        // 更新库存
        Long productInventoryVersion = inventoryDetails.getVersion();
        productInventoryApi.reductionForTcc(productUid, productNum, productInventoryVersion);
        // 更新账户余额
        Integer userInfoAmountVersion = userInfoAmountOutPut.getVersion();
        userAmountInfoApi.settlementForTcc(userUid, userInfoAmountVersion, productAmount);

        return Result.success();
    }

}
