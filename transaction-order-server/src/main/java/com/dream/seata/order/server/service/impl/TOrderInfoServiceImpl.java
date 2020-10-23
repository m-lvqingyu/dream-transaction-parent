package com.dream.seata.order.server.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import com.dream.seata.order.server.mapper.TOrderInfoMapper;
import com.dream.seata.order.server.model.TOrderInfo;
import com.dream.seata.order.server.model.TOrderInfoExample;
import com.dream.seata.order.server.service.TOrderInfoService;
import com.dream.seata.user.api.SysUserInfoApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/06 22:55
 */
@Service
public class TOrderInfoServiceImpl implements TOrderInfoService {

    @Autowired
    private TOrderInfoMapper tOrderInfoMapper;
    @Autowired
    private SysUserInfoApi sysUserInfoApi;

    @Override
    public OrderInfoOutPut findOrderInfoByOrderId(String orderId) {
        TOrderInfoExample example = new TOrderInfoExample();
        example.createCriteria().andOrderUidEqualTo(orderId);
        List<TOrderInfo> orderInfoList = tOrderInfoMapper.selectByExample(example);
        if (orderInfoList == null || orderInfoList.isEmpty()) {
            return null;
        }
        TOrderInfo tOrderInfo = orderInfoList.get(0);
        OrderInfoOutPut orderInfoOutPut = new OrderInfoOutPut();
        BeanUtils.copyProperties(tOrderInfo, orderInfoOutPut);
        return orderInfoOutPut;
    }

    @Override
    public Result createOrderInfo(OrderInfoInPut orderInfoInPut) {
        TOrderInfo tOrderInfo = new TOrderInfo();
        BeanUtils.copyProperties(orderInfoInPut, tOrderInfo);
        Snowflake snowflake = IdUtil.getSnowflake(1,1);
        long nextId = snowflake.nextId();
        String orderUid = String.valueOf(nextId);
        tOrderInfo.setOrderUid(orderUid);
        Date currentTime = new Date();
        tOrderInfo.setCreateTime(currentTime);
        tOrderInfo.setUpdateTime(currentTime);
        int id = tOrderInfoMapper.insertSelective(tOrderInfo);
        if (id <= 0) {
            return Result.custom(ResultCode.ORDER_CREATE_ERROR);
        }
        String userUid = orderInfoInPut.getUserUid();
        sysUserInfoApi.userInfoSettlement(userUid, new BigDecimal("10"));
        return Result.success(orderUid);
    }

    public static void main(String[] args) {
        Snowflake snowflake = IdUtil.getSnowflake(1,1);
        long nextId = snowflake.nextId();
        System.out.println(nextId);

    }
}
