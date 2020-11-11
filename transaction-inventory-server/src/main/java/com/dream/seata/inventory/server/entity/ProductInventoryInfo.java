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
     * 商品数量
     */
    private Long currentInventoryNum;

    /**
     * 已售商品数量
     */
    private Long lockNum;

    /**
     * 版本号
     */
    private Long version;

    private Date createTime;

    private Date updateTime;

}
