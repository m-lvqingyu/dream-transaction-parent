package com.dream.seata.user.api;

import com.dream.seata.user.api.output.UserInfoOutPut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
     * @return 用户信息
     */
    @GetMapping("/loginUserInfoDetails")
    UserInfoOutPut loginUserInfoDetails(HttpServletRequest request);
}
