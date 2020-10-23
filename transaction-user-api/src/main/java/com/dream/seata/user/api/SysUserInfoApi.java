package com.dream.seata.user.api;

import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.api.output.UserInfoOutPut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@RequestMapping("/user")
@FeignClient(value = "TRANSACTION-USER-SERVER")
public interface SysUserInfoApi {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/info/{username}")
    UserInfoOutPut loadUserByUsername(@PathVariable("username") String username);

    /**
     * 获取当前登录用户的用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/loginUserInfoDetails")
    UserInfoOutPut loginUserInfoDetails(HttpServletRequest request);

    /**
     * 根据ID，获取用户信息
     *
     * @param userUid 用户UID
     * @return
     */
    @GetMapping("/userInfoDetailsByUid/{userUid}")
    UserInfoOutPut userInfoDetailsByUid(@PathVariable("userUid") String userUid);

    /**
     * 根据UID，获取用户账户信息
     *
     * @param userUid 用户UId
     * @return
     */
    @GetMapping("/userInfoAmountDetailsByUid/{userUid}")
    UserInfoAmountOutPut userInfoAmountDetailsByUid(@PathVariable("userUid") String userUid);

    /**
     * 订单结算，扣减账户金额
     *
     * @param userUid
     * @param deductionAmount
     * @return
     */
    @PostMapping("/userInfoSettlement")
    Result userInfoSettlement(@RequestParam("userUid") String userUid, @RequestParam("deductionAmount") BigDecimal deductionAmount);

}
