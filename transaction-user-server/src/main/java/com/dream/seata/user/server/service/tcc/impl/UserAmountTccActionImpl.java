package com.dream.seata.user.server.service.tcc.impl;

import com.dream.seata.core.result.ResultHolder;
import com.dream.seata.core.utils.BigDecimalUtils;
import com.dream.seata.user.server.helper.UserAmountInfoHelper;
import com.dream.seata.user.server.service.tcc.UserAmountTccAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAmountTccActionImpl implements UserAmountTccAction {

    @Autowired
    private UserAmountInfoHelper userAmountInfoHelper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean prepareSettlement(BusinessActionContext businessActionContext, String userUid, Integer version, BigDecimal deductionAmount) {
        userAmountInfoHelper.settlement(userUid, deductionAmount, version);
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建user-settlement第一阶段提交]-用户UID:{},金额:{},版本号:{},xid:{}", userUid, deductionAmount, version, xid);
        ResultHolder.setResult(getClass(), xid, userUid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建user-settlement第二阶段提交]-xid:{}", xid);
        String userUid = ResultHolder.getResult(getClass(), xid);
        if (StringUtils.isBlank(userUid)) {
            return true;
        }
        Map<String, Object> actionContext = businessActionContext.getActionContext();
        log.info("[TCC事务]-[创建user-settlement第二阶段提交]-ActionContext:{}", actionContext);
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建user-settlement第二阶段回滚]-xid:{}", xid);
        String userUid = ResultHolder.getResult(getClass(), xid);
        if (StringUtils.isBlank(userUid)) {
            return true;
        }
        Map<String, Object> actionContext = businessActionContext.getActionContext();
        log.info("[TCC事务]-[创建user-settlement第二阶段回滚]-ActionContext:{}", actionContext);
        Object object = businessActionContext.getActionContext("deductionAmount");
        BigDecimal deductionAmount = BigDecimalUtils.objectConvertBigDecimal(object);
        userAmountInfoHelper.addUserMainAmount(userUid, deductionAmount);
        //回滚结束时，删除标识
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }
}
