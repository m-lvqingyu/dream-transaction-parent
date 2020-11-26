package com.dream.seata.inventory.server.controller.inward;

import com.dream.seata.core.result.Result;
import com.dream.seata.inventory.api.ProductInventoryApi;
import com.dream.seata.inventory.api.output.ProductInventoryInfoOutPut;
import com.dream.seata.inventory.server.service.at.ProductInventoryInfoForAtService;
import com.dream.seata.inventory.server.service.tcc.ProductInventoryInfoForTccService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lv.QingYu
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductInventoryInwardController implements ProductInventoryApi {

    private final ProductInventoryInfoForAtService productInventoryInfoForAtService;

    private final ProductInventoryInfoForTccService productInventoryInfoForTccService;

    @Override
    public ProductInventoryInfoOutPut getInventoryDetails(String productUid) {
        return productInventoryInfoForAtService.findProductInventoryInfo(productUid);
    }

    @Override
    public Result reductionForAt(String productUid, Integer productNum, Long version) {
        Result result = productInventoryInfoForAtService.reductionProductInventory(productUid, productNum, version);
        if(productNum == 10){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Result reductionForTcc(String productUid, Integer productNum, Long version) {
        return productInventoryInfoForTccService.reductionProductInventory(productUid, productNum, version);
    }


}
