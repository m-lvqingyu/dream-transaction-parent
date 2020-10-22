package com.dream.seata.user.server.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class SysUserAmountInfo {
    private Integer id;

    private String userUid;

    private BigDecimal mainAmount;

    private BigDecimal subAmount;

    private BigDecimal frozenAmount;

    private Integer version;

    private Date createTime;

    private Date updateTime;

}