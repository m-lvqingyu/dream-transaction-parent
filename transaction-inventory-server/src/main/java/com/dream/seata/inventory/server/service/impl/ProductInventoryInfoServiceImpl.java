package com.dream.seata.inventory.server.service.impl;

import com.dream.seata.core.exception.DreamCoreException;
import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import com.dream.seata.inventory.server.entity.ProductInventoryInfo;
import com.dream.seata.inventory.server.helper.ProductInventoryInfoHelper;
import com.dream.seata.inventory.server.service.ProductInventoryInfoService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lv.QingYu
 */
@Service
public class ProductInventoryInfoServiceImpl implements ProductInventoryInfoService {

    @Autowired
    private ProductInventoryInfoHelper productInventoryInfoHelper;

    @Override
    @GlobalTransactional
    public Result reductionProductInventory(String productUid, Integer productNum, Long version) {
        int result = productInventoryInfoHelper.updateProductInventory(productUid, productNum, version);
        if (result <= 0) {
            throw new DreamCoreException(ResultCode.PRODUCT_INVENTORY_NOT_ENOUGH_ERROR);
        }
        return Result.success();
    }

    @Override
    public ProductInventoryInfoOutPut findProductInventoryInfo(String productUid) {
        ProductInventoryInfo productInventoryInfo = productInventoryInfoHelper.findProductInventoryInfo(productUid);
        if (productInventoryInfo == null) {
            return null;
        }
        ProductInventoryInfoOutPut productInventoryInfoOutPut = new ProductInventoryInfoOutPut();
        BeanUtils.copyProperties(productInventoryInfo, productInventoryInfoOutPut);
        return productInventoryInfoOutPut;
    }
}
