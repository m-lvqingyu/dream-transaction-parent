package com.dream.seata.user.api.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Create By 2020/10/22
 *
 * @author lvqingyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAmountOutPut {

    private String userUid;

    private BigDecimal mainAmount;

    private BigDecimal subAmount;

    private BigDecimal frozenAmount;
}
