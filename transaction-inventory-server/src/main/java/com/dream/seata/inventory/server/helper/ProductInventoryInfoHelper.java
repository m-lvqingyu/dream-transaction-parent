package com.dream.seata.inventory.server.helper;

import com.dream.seata.inventory.server.dao.ProductInventoryInfoMapper;
import com.dream.seata.inventory.server.entity.ProductInventoryInfo;
import com.dream.seata.inventory.server.entity.ProductInventoryInfoExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lv.QingYu
 */
@Component
public class ProductInventoryInfoHelper {

    @Autowired
    private ProductInventoryInfoMapper productInventoryInfoMapper;

    /**
     * 根据商品ID与版本号，扣减库存
     *
     * @param productUid 商品ID
     * @param productNum 扣减商品数量
     * @param version    版本号
     * @return
     */
    public int updateProductInventory(String productUid, Integer productNum, Long version) {
        ProductInventoryInfoExample example = buildExample(productUid, version);
        List<ProductInventoryInfo> infoList = productInventoryInfoMapper.selectByExample(example);
        if (infoList == null || infoList.isEmpty()) {
            return -9999;
        }
        ProductInventoryInfo productInventoryInfo = infoList.get(0);
        Long currentInventoryNum = productInventoryInfo.getCurrentInventoryNum();
        if (currentInventoryNum < productNum) {
            return -9999;
        }
        Long lockNum = productInventoryInfo.getLockNum();
        ProductInventoryInfo updateProductInventoryInfo = new ProductInventoryInfo();
        updateProductInventoryInfo.setCurrentInventoryNum(currentInventoryNum - productNum);
        updateProductInventoryInfo.setLockNum(lockNum + productNum);
        updateProductInventoryInfo.setVersion(version + 1);
        int result = productInventoryInfoMapper.updateByExampleSelective(updateProductInventoryInfo, example);
        return result;
    }

    public int addProductInventory(String productUid, Integer productNum) {
        ProductInventoryInfo productInventoryInfo = findProductInventoryInfo(productUid);
        if (productInventoryInfo == null) {
            return -9999;
        }
        long lockNum = productInventoryInfo.getLockNum();
        long currentInventoryNum = productInventoryInfo.getCurrentInventoryNum();
        ProductInventoryInfo updateProductInventoryInfo = new ProductInventoryInfo();
        updateProductInventoryInfo.setLockNum(lockNum - productNum);
        updateProductInventoryInfo.setCurrentInventoryNum(currentInventoryNum + productNum);
        ProductInventoryInfoExample example = buildExample(productUid, null);
        int result = productInventoryInfoMapper.updateByExampleSelective(updateProductInventoryInfo, example);
        return result;
    }

    public ProductInventoryInfo findProductInventoryInfo(String productUid) {
        ProductInventoryInfoExample example = buildExample(productUid, null);
        List<ProductInventoryInfo> infoList = productInventoryInfoMapper.selectByExample(example);
        if (infoList == null || infoList.isEmpty()) {
            return null;
        }
        ProductInventoryInfo productInventoryInfo = infoList.get(0);
        return productInventoryInfo;
    }

    private ProductInventoryInfoExample buildExample(String productUid, Long version) {
        ProductInventoryInfoExample example = new ProductInventoryInfoExample();
        ProductInventoryInfoExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(productUid)) {
            criteria.andProductUidEqualTo(productUid);
        }
        if (version != null) {
            criteria.andVersionEqualTo(version);
        }
        return example;
    }
}
