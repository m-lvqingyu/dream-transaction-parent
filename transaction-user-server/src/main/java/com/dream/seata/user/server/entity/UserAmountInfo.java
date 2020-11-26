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
    private Integer id;

    private String userUid;

    private BigDecimal mainAmount;

    private BigDecimal subAmount;

    private BigDecimal frozenAmount;

    @Version
    private Integer version;

    private Date createTime;

    private Date updateTime;

}
