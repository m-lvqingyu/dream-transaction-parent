package com.dream.sharding.sphere.service;

import com.dream.seata.core.result.Result;
import com.dream.sharding.sphere.dto.UserInfoDTO;

/**
 * @author lv.QingYu
 */
public interface IUserInfoService {

    /**
     * 新增用户
     *
     * @param userInfoDTO
     * @return
     */
    Result saveUser(UserInfoDTO userInfoDTO);
}
