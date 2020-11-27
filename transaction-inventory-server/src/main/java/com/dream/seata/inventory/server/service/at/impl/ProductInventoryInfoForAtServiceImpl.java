package com.dream.seata.inventory.server.service.at.impl;

import com.dream.seata.core.exception.DreamCoreException;
import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import com.dream.seata.inventory.server.dao.ProductInventoryInfoMapper;
import com.dream.seata.inventory.server.entity.ProductInventoryInfo;
import com.dream.seata.inventory.server.service.at.ProductInventoryInfoForAtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lv.QingYu
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductInventoryInfoForAtServiceImpl implements ProductInventoryInfoForAtService {

    private final ProductInventoryInfoMapper productInventoryInfoMapper;

    @Override
    public Result reductionProductInventory(String productUid, Long productNum, Long version) {
        int result = productInventoryInfoMapper.updateStock(productUid, version, productNum);
        if (result <= 0) {
            throw new DreamCoreException(ResultCode.PRODUCT_INVENTORY_NOT_ENOUGH_ERROR);
        }
        return Result.success();
    }

    @Override
    public ProductInventoryInfoOutPut findProductInventoryInfo(String productUid) {
        ProductInventoryInfo productInventoryInfo = productInventoryInfoMapper.selectByUidAndVs(productUid, null);
        if (productInventoryInfo == null) {
            return null;
        }
        ProductInventoryInfoOutPut productInventoryInfoOutPut = new ProductInventoryInfoOutPut();
        BeanUtils.copyProperties(productInventoryInfo, productInventoryInfoOutPut);
        return productInventoryInfoOutPut;
    }
}
