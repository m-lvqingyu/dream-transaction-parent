package com.dream.seata.user.server.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * user_amount_info
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Version
    private Long version;

    private Date createTime;

    private Date updateTime;

}
