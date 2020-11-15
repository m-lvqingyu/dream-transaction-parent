package com.dream.seata.order.server.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.inventory.api.ProductInventoryApi;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import com.dream.seata.order.server.entity.OrderInfo;
import com.dream.seata.order.server.helper.OrderInfoHelper;
import com.dream.seata.order.server.service.OrderInfoService;
import com.dream.seata.user.api.UserAmountInfoApi;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/06 22:55
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoHelper orderInfoHelper;
    @Autowired
    private UserAmountInfoApi userAmountInfoApi;
    @Autowired
    private ProductInventoryApi productInventoryApi;

    @Override
    public OrderInfoOutPut findOrderInfoByOrderId(String orderUid) {
        List<OrderInfo> orderInfoList = orderInfoHelper.findOrderInfo(orderUid);
        if (orderInfoList == null || orderInfoList.isEmpty()) {
            log.warn("[查询订单详情]-根据订单ID:{}未获取到订单信息", orderUid);
            return null;
        }
        OrderInfo orderInfo = orderInfoList.get(0);
        OrderInfoOutPut orderInfoOutPut = new OrderInfoOutPut();
        BeanUtils.copyProperties(orderInfo, orderInfoOutPut);
        return orderInfoOutPut;
    }

    @Override
    @GlobalTransactional
    public Result createOrderInfo(OrderInfoInPut orderInfoInPut) {
        String productUid = orderInfoInPut.getProductUid();
        ProductInventoryInfoOutPut productInventoryInfo = productInventoryApi.findProductInventoryInfo(productUid);
        if (productInventoryInfo == null) {
            log.warn("[创建订单]-根据商品ID:{}，未获取到商品库存信息！", productUid);
            return Result.custom(ResultCode.PRODUCT_INVENTORY_NOT_ENOUGH_ERROR);
        }
        String userUid = orderInfoInPut.getUserUid();
        UserInfoAmountOutPut userInfoAmountOutPut = userAmountInfoApi.amountDetails(userUid);
        if (userInfoAmountOutPut == null) {
            log.warn("[创建订单]-根据用户ID:{}，未获取到用户账户信息！", userUid);
            return Result.custom(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        // 创建订单
        OrderInfo orderInfo = buildOrderInfo(orderInfoInPut);
        int id = orderInfoHelper.saveOrderInfo(orderInfo);
        if (id <= 0) {
            return Result.custom(ResultCode.ORDER_CREATE_ERROR);
        }
        Integer userAmountVersion = userInfoAmountOutPut.getVersion();
        // 扣减用户金额
        userAmountInfoApi.settlement(userUid, userAmountVersion, new BigDecimal("10"));
        // 扣减库存
        Long productVersion = productInventoryInfo.getVersion();
        Integer productNum = orderInfoInPut.getProductNum();
        productInventoryApi.reductionProductInventory(productUid, productNum, productVersion);
        String orderUid = orderInfo.getOrderUid();
        return Result.success(orderUid);
    }

    private OrderInfo buildOrderInfo(OrderInfoInPut orderInfoInPut) {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoInPut, orderInfo);
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long nextId = snowflake.nextId();
        String orderUid = String.valueOf(nextId);
        orderInfo.setOrderUid(orderUid);
        Date currentTime = new Date();
        orderInfo.setCreateTime(currentTime);
        orderInfo.setUpdateTime(currentTime);
        return orderInfo;
    }

    public static void main(String[] args) {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long nextId = snowflake.nextId();
        System.out.println(nextId);

    }
}
