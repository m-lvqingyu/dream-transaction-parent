package com.dream.seata.user.api.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoInPut {

    private String userUid;

    private BigDecimal amount;
}
