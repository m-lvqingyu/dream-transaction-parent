package com.dream.sharding.sphere.mapper;

import com.dream.sharding.sphere.entity.UserInfo;

/**
 * @author Lv.QingYu
 */
public interface UserInfoMapper {

    /**
     * 根据用户唯一ID，获取用户基础信息
     *
     * @param uid
     * @return
     */
    UserInfo getUserInfoByUid(String uid);

    /**
     * 新增用户基础信息
     *
     * @param userInfo
     * @return
     */
    Long insertUserInfo(UserInfo userInfo);
}
