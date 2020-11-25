package com.dream.seata.user.api;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@RequestMapping("/inward/user")
public interface UserAmountInfoApi {

    /**
     * 根据UID，获取用户账户信息
     *
     * @param userUid 用户UId
     * @return
     */
    @GetMapping("v1/amountDetails/{userUid}")
    UserInfoAmountOutPut amountDetails(@PathVariable("userUid") String userUid);

    /**
     * 订单结算，扣减账户金额
     *
     * @param userUid         用户唯一ID
     * @param version         版本号
     * @param deductionAmount 扣减金额
     * @return
     */
    @PostMapping("v1/settlementForAt")
    Result settlementForAt(@RequestParam("userUid") String userUid,
                           @RequestParam("version") Integer version,
                           @RequestParam("deductionAmount") BigDecimal deductionAmount);

    /**
     * 订单结算，扣减账户金额
     *
     * @param userUid         用户唯一ID
     * @param version         版本号
     * @param deductionAmount 扣减金额
     * @return
     */
    @PostMapping("v1/settlementForTcc")
    Result settlementForTcc(@RequestParam("userUid") String userUid,
                            @RequestParam("version") Integer version,
                            @RequestParam("deductionAmount") BigDecimal deductionAmount);

}
