package com.dream.seata.order.server.service.tcc.impl;

import com.dream.seata.core.result.ResultHolder;
import com.dream.seata.order.server.entity.OrderInfo;
import com.dream.seata.order.server.helper.OrderInfoHelper;
import com.dream.seata.order.server.service.tcc.OrderInfoTccAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Service
public class OrderInfoTccActionImpl implements OrderInfoTccAction {

    @Autowired
    private OrderInfoHelper orderInfoHelper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean prepareCreateOrder(BusinessActionContext businessActionContext, String userUid, String recipientUid, String productUid) {
        String orderUid = orderInfoHelper.findOrderUid();
        OrderInfo orderInfo = buildOrderInfo(orderUid, userUid, recipientUid, productUid);
        int orderId = orderInfoHelper.saveOrderInfo(orderInfo);
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建order第一阶段提交]-订单UID:{},订单ID:{},xid:{}", orderUid, orderId, xid);
        ResultHolder.setResult(getClass(), xid, orderUid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建order第二阶段提交]-xid:{}", xid);
        // 防止幂等性，如果commit阶段重复执行则直接返回
        String orderUid = ResultHolder.getResult(getClass(), xid);
        if (StringUtils.isBlank(orderUid)) {
            return true;
        }
        Map<String, Object> actionContext = businessActionContext.getActionContext();
        log.info("[TCC事务]-[创建order第二阶段提交]-ActionContext:{}", actionContext);
        //提交成功是删除标识
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建order第二阶段回滚]-xid:{}", xid);
        String orderUid = ResultHolder.getResult(getClass(), xid);
        if (StringUtils.isBlank(orderUid)) {
            return true;
        }
        Map<String, Object> actionContext = businessActionContext.getActionContext();
        log.info("[TCC事务]-[创建order第二阶段回滚]-ActionContext:{}", actionContext);
        orderInfoHelper.delOrderInfo(orderUid);
        //回滚结束时，删除标识
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }

    private OrderInfo buildOrderInfo(String orderUid, String userUid, String recipientUid, String productUid) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderUid(orderUid);
        orderInfo.setUserUid(userUid);
        orderInfo.setRecipientUid(recipientUid);
        orderInfo.setProductUid(productUid);
        Date currentDate = new Date();
        orderInfo.setCreateTime(currentDate);
        orderInfo.setUpdateTime(currentDate);
        return orderInfo;
    }

}
