package com.dream.sharding.sphere.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAmountInfoVO {

    /**
     * 用户唯一ID
     */
    private String userUid;

    /**
     * 主账户金额
     */
    private BigDecimal mainAmount;

    /**
     * 子账户金额
     */
    private BigDecimal subAmount;

    /**
     * 冻结金额
     */
    private BigDecimal frozenAmount;

    /**
     * 版本编号
     */
    private Long version;

}
