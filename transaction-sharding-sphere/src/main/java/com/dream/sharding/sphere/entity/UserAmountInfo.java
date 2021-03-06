package com.dream.sharding.sphere.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户账户信息
 * </p>
 *
 * @author Lv.QingYu
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAmountInfo {

    private Long id;

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

    private Date updateTime;

}
