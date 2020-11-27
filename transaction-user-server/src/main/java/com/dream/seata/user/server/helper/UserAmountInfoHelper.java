package com.dream.seata.user.server.helper;

import com.dream.seata.user.server.dao.UserAmountInfoMapper;
import com.dream.seata.user.server.entity.UserAmountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
public class UserAmountInfoHelper {

    @Autowired
    private UserAmountInfoMapper userAmountInfoMapper;

    /**
     * 根据用户UID获取用户账户信息
     *
     * @param userUid 用户唯一ID
     * @return
     */
    public UserAmountInfo getUserAmountInfo(String userUid) {
        UserAmountInfo userAmountInfo = userAmountInfoMapper.selectByUidAndVs(userUid, null);
        return userAmountInfo;
    }

    public int deductionMainAmount(String userUid, Long version, BigDecimal deductionAmount) {
        int result = userAmountInfoMapper.deductionMainAmount(userUid, version, deductionAmount);
        return result;
    }

}
