package com.dream.seata.order.server.service.at.impl;

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
import com.dream.seata.order.server.service.at.OrderInfoForAtService;
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
public class OrderInfoForAtServiceImpl implements OrderInfoForAtService {

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
    @GlobalTransactional(name = "at-create-order", rollbackFor = Exception.class, timeoutMills = 6000)
    public Result createOrderInfo(OrderInfoInPut orderInfoInPut) {
        String productUid = orderInfoInPut.getProductUid();
        ProductInventoryInfoOutPut productInventoryInfo = productInventoryApi.getInventoryDetails(productUid);
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
        OrderInfo orderInfo = orderInfoHelper.buildOrderInfo(orderInfoInPut);
        orderInfoHelper.saveOrderInfo(orderInfo);
        long productNum = orderInfoInPut.getProductNum();
        if(productNum == 2){
            throw new RuntimeException("分布式事务-创建订单异常");
        }

        // 扣减用户金额
        Long userAmountVersion = userInfoAmountOutPut.getVersion();
        Result settlementResult = userAmountInfoApi.settlementForAt(userUid, userAmountVersion, new BigDecimal("10"));
        if(settlementResult == null || !settlementResult.getCode().equals(ResultCode.SUCCESS.getCode())){
            throw new RuntimeException("分布式事务-用户扣款异常");
        }
        if(productNum == 3){
            throw new RuntimeException("分布式事务-用户扣款异常");
        }
        // 扣减库存
        Long productVersion = productInventoryInfo.getVersion();
        // 扣减商品库存接口存在降级处理逻辑，异常被捕获，下游服务没有抛出，所以事务并不会回滚。这里就需要针对接口返回结果进行解析,如果返回不正确，则抛出异常
        Result reductionResult = productInventoryApi.reductionForAt(productUid, productNum, productVersion);
        if(reductionResult == null || !reductionResult.getCode().equals(ResultCode.SUCCESS.getCode())){
            throw new RuntimeException("分布式事务-库存扣减异常");
        }
        if(productNum == 4){
            throw new RuntimeException("分布式事务-创建订单异常测试");
        }
        String orderUid = orderInfo.getOrderUid();
        return Result.success(orderUid);
    }

}
