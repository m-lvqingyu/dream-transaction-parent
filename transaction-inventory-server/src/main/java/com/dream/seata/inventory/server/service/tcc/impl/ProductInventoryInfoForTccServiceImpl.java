package com.dream.seata.inventory.server.service.tcc.impl;

import com.dream.seata.core.result.Result;
import com.dream.seata.inventory.server.service.tcc.ProductInventoryInfoForTccService;
import com.dream.seata.inventory.server.service.tcc.ProductInventoryTccAction;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lv.QingYu
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductInventoryInfoForTccServiceImpl implements ProductInventoryInfoForTccService {

    private final ProductInventoryTccAction productInventoryTccAction;

    @GlobalTransactional
    @Override
    public Result reductionProductInventory(String productUid, Integer productNum, Long version) {
        /*if(productNum == 2){
            throw new RuntimeException("分布式事务-Tcc模式测试");
        }*/
        productInventoryTccAction.prepareReductionProductInventory(null, productUid, productNum, version);
        /*if(productNum == 3){
            throw new RuntimeException("分布式事务-Tcc模式测试");
        }*/
        return Result.success();
    }
}
