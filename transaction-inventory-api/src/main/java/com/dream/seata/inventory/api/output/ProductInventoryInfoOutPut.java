package com.dream.seata.inventory.api.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lv.QingYu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryInfoOutPut {

    /**
     * 商品唯一ID
     */
    private String productUid;

    /**
     * 商品库存数量
     */
    private Long currentNum;

    /**
     * 已售商品数量
     */

    private Long soldNum;

    /**
     * 已锁定商品数量
     */
    private Long lockNum;

    /**
     * 版本号
     */
    private Long version;

}
