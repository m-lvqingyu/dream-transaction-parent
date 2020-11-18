package com.dream.seata.user.server.helper;

import com.dream.seata.core.exception.DreamCoreException;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.user.server.dao.UserAmountInfoMapper;
import com.dream.seata.user.server.entity.UserAmountInfo;
import com.dream.seata.user.server.entity.UserAmountInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

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
        UserAmountInfoExample example = new UserAmountInfoExample();
        example.createCriteria().andUserUidEqualTo(userUid);
        example.setLimit(1);
        List<UserAmountInfo> userAmountInfoList = userAmountInfoMapper.selectByExample(example);
        if (userAmountInfoList == null || userAmountInfoList.isEmpty()) {
            return null;
        }
        return userAmountInfoList.get(0);
    }

    /**
     * 根据用户唯一ID和版本号，更新用户账户信息
     *
     * @param userUid              用户唯一ID
     * @param version              版本号
     * @param updateUserAmountInfo 需要更新的内容
     * @return
     */
    public int updateUserAmountInfo(String userUid, Integer version, UserAmountInfo updateUserAmountInfo) {
        UserAmountInfoExample example = new UserAmountInfoExample();
        UserAmountInfoExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(userUid)){
            criteria.andUserUidEqualTo(userUid);
        }
        if(version != null && version >= 0){
            criteria.andVersionEqualTo(version);
        }
        int result = userAmountInfoMapper.updateByExampleSelective(updateUserAmountInfo, example);
        return result;
    }

    public int settlement(String userUid, BigDecimal deductionAmount, Integer version) {
        UserAmountInfo userAmountInfo = getUserAmountInfo(userUid);
        if (userAmountInfo == null) {
            log.warn("[扣减用户账户余额]-根据用户UID:{}未获取到用户账户信息", userUid);
            throw new DreamCoreException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        BigDecimal mainAmount = userAmountInfo.getMainAmount();
        if (mainAmount.compareTo(deductionAmount) < 0) {
            log.warn("[扣减用户账户余额]-用户账户余额不足，用户UID:{}", userUid);
            throw new DreamCoreException(ResultCode.USER_ACCOUNT_INSUFFICIENT_BALANCE);
        }
        BigDecimal amount = mainAmount.subtract(deductionAmount);
        UserAmountInfo updateUserAmountInfo = new UserAmountInfo();
        updateUserAmountInfo.setMainAmount(amount);
        updateUserAmountInfo.setVersion(version + 1);
        int result = updateUserAmountInfo(userUid, version, updateUserAmountInfo);
        return result;
    }

    public int addUserMainAmount(String userUid, BigDecimal deductionAmount) {
        UserAmountInfo userAmountInfo = getUserAmountInfo(userUid);
        if (userAmountInfo == null) {
            log.warn("[扣减用户账户余额-回滚]-根据用户UID:{}未获取到用户账户信息", userUid);
            throw new DreamCoreException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        BigDecimal mainAmount = userAmountInfo.getMainAmount();
        BigDecimal amount = mainAmount.add(deductionAmount);
        UserAmountInfo updateUserAmountInfo = new UserAmountInfo();
        updateUserAmountInfo.setMainAmount(amount);
        int result = updateUserAmountInfo(userUid, null, updateUserAmountInfo);
        return result;
    }

}
