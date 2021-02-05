package com.dream.sharding.sphere.service;

import com.dream.sharding.sphere.vo.UserAmountInfoVO;

/**
 * @author Lv.QingYu
 * @since 2021-02-01
 */
public interface IUserAmountInfoService {

    /**
     * 根据ID获取用户账户信息
     *
     * @param Id
     * @return
     */
    UserAmountInfoVO getUserAmountById(Long Id);

}
