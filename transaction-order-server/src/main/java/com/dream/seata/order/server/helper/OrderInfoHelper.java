package com.dream.seata.order.server.helper;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.server.dao.OrderInfoMapper;
import com.dream.seata.order.server.entity.OrderInfo;
import com.dream.seata.order.server.entity.OrderInfoExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Lv.QingYu
 */
@Component
public class OrderInfoHelper {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public List<OrderInfo> findOrderInfo(String orderUid) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andOrderUidEqualTo(orderUid);
        example.setLimit(1);
        List<OrderInfo> orderInfoList = orderInfoMapper.selectByExample(example);
        return orderInfoList;
    }

    public int saveOrderInfo(OrderInfo orderInfo) {
        return orderInfoMapper.insertSelective(orderInfo);
    }

    public int delOrderInfo(String orderUid) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andOrderUidEqualTo(orderUid);
        return orderInfoMapper.deleteByExample(example);
    }

    public OrderInfo buildOrderInfo(OrderInfoInPut orderInfoInPut) {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoInPut, orderInfo);
        String orderUid =  findOrderUid();
        orderInfo.setOrderUid(orderUid);
        Date currentTime = new Date();
        orderInfo.setCreateTime(currentTime);
        orderInfo.setUpdateTime(currentTime);
        return orderInfo;
    }

    public String findOrderUid() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long nextId = snowflake.nextId();
        String orderUid = String.valueOf(nextId);
        return orderUid;
    }

}
