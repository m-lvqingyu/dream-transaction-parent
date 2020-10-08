package com.dream.seata.order.server.service.impl;

import com.dream.seata.order.api.input.OrderInfoInPut;
import com.dream.seata.order.api.output.OrderInfoOutPut;
import com.dream.seata.order.server.mapper.TOrderInfoMapper;
import com.dream.seata.order.server.model.TOrderInfo;
import com.dream.seata.order.server.model.TOrderInfoExample;
import com.dream.seata.order.server.service.TOrderInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String createOrderInfo(OrderInfoInPut orderInfoInPut) {
        TOrderInfo tOrderInfo = new TOrderInfo();
        BeanUtils.copyProperties(orderInfoInPut, tOrderInfo);
        String orderUid = UUID.randomUUID().toString();
        tOrderInfo.setOrderUid(orderUid);
        Date currentTime = new Date();
        tOrderInfo.setCreateTime(currentTime);
        tOrderInfo.setUpdateTime(currentTime);
        int id = tOrderInfoMapper.insertSelective(tOrderInfo);
        if (id <= 0) {
            return null;
        }
        return orderUid;
    }
}
