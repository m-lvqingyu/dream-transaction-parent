package com.dream.seata.user.server.dao;

import com.dream.seata.user.server.entity.UserAmountInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
public interface UserAmountInfoMapper {

    /**
     * 根据用户Uid和版本编号，获取用户账户信息
     *
     * @param userUid 用户唯一ID
     * @param version 版本编号
     * @return
     */
    UserAmountInfo selectByUidAndVs(@Param("userUid") String userUid, @Param("version") Long version);


    /**
     * 扣减主账户金额-TCC模式一阶段提交
     *
     * @param userUid 用户唯一ID
     * @param version 版本编号
     * @param amount  金额
     * @return
     */
    int TX1AmountSubmit(@Param("userUid") String userUid, @Param("version") long version, @Param("amount") BigDecimal amount);

    /**
     * 扣减主账户金额-TCC模式二阶段提交
     *
     * @param userUid 用户唯一ID
     * @param amount  金额
     * @return
     */
    int TX2AmountSubmit(@Param("userUid") String userUid, @Param("amount") BigDecimal amount);

    /**
     * 扣减主账户金额-TCC模式二阶段回滚
     *
     * @param userUid 用户唯一ID
     * @param amount  金额
     * @return
     */
    int TX2AmountFallBack(@Param("userUid") String userUid, @Param("amount") BigDecimal amount);

    /**
     * 扣减主账户金额
     *
     * @param userUid 用户唯一ID
     * @param version 版本号
     * @param amount  金额
     * @return
     */
    int deductionMainAmount(@Param("userUid") String userUid, @Param("version") long version, @Param("amount") BigDecimal amount);
}
