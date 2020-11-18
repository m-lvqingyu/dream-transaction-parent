package com.dream.seata.inventory.server.service.tcc.impl;

import com.dream.seata.core.result.ResultHolder;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean prepareReductionProductInventory(BusinessActionContext businessActionContext, String productUid, Integer productNum, Long version) {
        productInventoryInfoHelper.updateProductInventory(productUid, productNum, version);
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建product-inventory第一阶段提交]-商品UID:{},商品数量:{},版本号:{},xid:{}", productUid, productNum, version, xid);
        ResultHolder.setResult(getClass(), xid, productUid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建product-inventory第二阶段提交]-xid:{}", xid);
        // 防止幂等性，如果commit阶段重复执行则直接返回
        String productUid = ResultHolder.getResult(getClass(), xid);
        if (StringUtils.isBlank(productUid)) {
            return true;
        }
        Map<String, Object> actionContext = businessActionContext.getActionContext();
        log.info("[TCC事务]-[创建product-inventory第二阶段提交]-ActionContext:{}", actionContext);
        //提交成功是删除标识
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("[TCC事务]-[创建product-inventory第二阶段回滚]-xid:{}", xid);
        String productUid = ResultHolder.getResult(getClass(), xid);
        if (StringUtils.isBlank(productUid)) {
            return true;
        }
        Map<String, Object> actionContext = businessActionContext.getActionContext();
        log.info("[TCC事务]-[创建product-inventory第二阶段回滚]-ActionContext:{}", actionContext);
        String productId = (String) businessActionContext.getActionContext("productUid");
        Integer productNum = (Integer) businessActionContext.getActionContext("productNum");
        productInventoryInfoHelper.addProductInventory(productId, productNum);
        //回滚结束时，删除标识
        ResultHolder.removeResult(getClass(), xid);
        return true;
    }
}
