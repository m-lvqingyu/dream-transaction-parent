package com.dream.seata.order.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/06 22:39
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoOutPut {

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
    private String productId;

}
