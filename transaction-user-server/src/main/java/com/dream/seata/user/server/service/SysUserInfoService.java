package com.dream.seata.user.server.service;

import com.dream.seata.user.api.output.UserInfoOutPut;

/**
 * @author Lv.QingYu
 */
public interface SysUserInfoService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户姓名
     * @return
     */
    UserInfoOutPut loadUserByUsername(String username);

    /**
     * 根据用户ID，获取当前登录用户信息
     *
     * @param userId 用户ID
     * @return
     */
    UserInfoOutPut loginUserInfoDetails(int userId);
}
