package com.dream.seata.user.server.service.tcc.impl;

import com.dream.seata.core.result.ResultHolder;
import com.dream.seata.user.server.dao.UserAmountInfoMapper;
import com.dream.seata.user.server.service.tcc.UserAmountTccAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAmountTccActionImpl implements UserAmountTccAction {

    private final UserAmountInfoMapper userAmountInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean prepareSettlement(BusinessActionContext businessActionContext, String userUid, Long version, BigDecimal deductionAmount) {
        int result = userAmountInfoMapper.TX1AmountSubmit(userUid, version, deductionAmount);
        if (result <= 0) {
            return false;
        }
        String xid = businessActionContext.getXid();
        String value = userUid + "," + deductionAmount.toString();
        ResultHolder.setResult(getClass(), xid, value);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        boolean result = submitOrFallback(false, businessActionContext);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        boolean result = submitOrFallback(true, businessActionContext);
        return result;
    }

    private boolean submitOrFallback(boolean isFallback, BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        String value = ResultHolder.getResult(getClass(), xid);
        if (isFallback) {
            log.info("[TCC事务]-[第二阶段回滚]-xid:{},value:{}", xid, value);
        } else {
            log.info("[TCC事务]-[第二阶段提交]-xid:{},value:{}", xid, value);
        }
        if (StringUtils.isBlank(value)) {
            return true;
        }
        String[] valueArray = value.split(",");
        BigDecimal amount = new BigDecimal(valueArray[1]);
        int result = 0;
        if (isFallback) {
            result = userAmountInfoMapper.TX2AmountFallBack(valueArray[0], amount);
        } else {
            result = userAmountInfoMapper.TX2AmountFallBack(valueArray[0], amount);
        }
        if (result <= 0) {
            return false;
        }
        //回滚结束时，删除标识
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }
}
