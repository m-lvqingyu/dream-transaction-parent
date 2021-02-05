package com.dream.sharding.sphere.mapper;

import com.dream.sharding.sphere.entity.UserAmountInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Lv.QingYu
 * @since 2021-02-01
 */
public interface UserAmountInfoMapper {

    /**
     * 根据ID查询账户信息
     *
     * @param id
     * @return
     */
    UserAmountInfo getUserAmountInfoById(@Param("id") Long id);

    /**
     * 根据用户唯一ID，新增用户账户信息
     *
     * @param userUid
     * @param updateTime
     * @return
     */
    Long insertUserAmount(@Param("userUid") String userUid, @Param("updateTime") Date updateTime);
}
