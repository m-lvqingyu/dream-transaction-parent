package com.dream.seata.inventory.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * product_inventory_info
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryInfo {
    private Long inventoryId;

    /**
     * 商品唯一ID
     */
    private String productUid;

    /**
     * 商品库存数量
     */
    private Long currentNum;

    /**
     * 已销售数量
     */
    private Long soldNum;

    /**
     * 已锁定数量
     */
    private Long lockNum;

    /**
     * 版本号
     */
    private Long version;

    private Date createTime;

    private Date updateTime;

}
