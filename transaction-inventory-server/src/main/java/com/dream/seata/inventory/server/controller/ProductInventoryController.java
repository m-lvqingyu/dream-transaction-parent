package com.dream.seata.inventory.server.controller;

import com.dream.seata.core.result.Result;
import com.dream.seata.inventory.api.ProductInventoryApi;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import com.dream.seata.inventory.server.service.ProductInventoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lv.QingYu
 */
@RestController
public class ProductInventoryController implements ProductInventoryApi {

    @Autowired
    private ProductInventoryInfoService productInventoryInfoService;

    @Override
    public Result reductionProductInventory(String productUid, Integer productNum, Long version) {
        return productInventoryInfoService.reductionProductInventory(productUid, productNum, version);
    }

    @Override
    public ProductInventoryInfoOutPut findProductInventoryInfo(String productUid) {
        return productInventoryInfoService.findProductInventoryInfo(productUid);
    }

}
