package com.dream.sharding.sphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Lv.QingYu
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_amount_info")
public class UserAmountInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户唯一ID
     */
    @TableField("user_uid")
    private String userUid;

    /**
     * 主账户金额
     */
    @TableField("main_amount")
    private BigDecimal mainAmount;

    /**
     * 子账户金额
     */
    @TableField("sub_amount")
    private BigDecimal subAmount;

    /**
     * 冻结金额
     */
    @TableField("frozen_amount")
    private BigDecimal frozenAmount;

    /**
     * 版本编号
     */
    @TableField("version")
    private Long version;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


}
