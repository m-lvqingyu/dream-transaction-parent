package com.dream.seata.order.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * order_info
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private Long id;

    /**
     * 订单ID
     */
    private String orderUid;

    /**
     * 预定人ID
     */
    private String userUid;

    /**
     * 收件人ID
     */
    private String recipientUid;

    /**
     * 商品ID
     */
    private String productUid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
}
