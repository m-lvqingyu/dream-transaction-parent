package com.dream.seata.inventory.server.service.tcc.impl;

import com.dream.seata.core.result.ResultHolder;
import com.dream.seata.inventory.server.dao.ProductInventoryInfoMapper;
import com.dream.seata.inventory.server.helper.ProductInventoryInfoHelper;
import com.dream.seata.inventory.server.service.tcc.ProductInventoryTccAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductInventoryTccActionImpl implements ProductInventoryTccAction {

    private final ProductInventoryInfoHelper productInventoryInfoHelper;

    private final ProductInventoryInfoMapper productInventoryInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean prepareReductionProductInventory(BusinessActionContext businessActionContext, String productUid, Long productNum, Long version) {
        int result = productInventoryInfoMapper.TX1StockSubmit(productUid, version, productNum);
        if (result <= 0) {
            return false;
        }
        String xid = businessActionContext.getXid();
        String value = productUid + "," + productNum;
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
        long num = Long.parseLong(valueArray[1]);
        int result = 0;
        if (isFallback) {
            result = productInventoryInfoMapper.TX2StockFallBack(valueArray[0], num);
        } else {
            result = productInventoryInfoMapper.TX2StockSubmit(valueArray[0], num);
        }
        if (result <= 0) {
            return false;
        }
        //回滚结束时，删除标识
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }
}
