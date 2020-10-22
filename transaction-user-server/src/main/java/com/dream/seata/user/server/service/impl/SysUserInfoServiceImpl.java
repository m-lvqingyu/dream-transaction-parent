package com.dream.seata.user.server.service.impl;

import com.dream.seata.core.enums.UserInfoStatus;
import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.api.output.UserInfoOutPut;
import com.dream.seata.user.server.mapper.SysUserAmountInfoMapper;
import com.dream.seata.user.server.mapper.SysUserInfoMapper;
import com.dream.seata.user.server.mapper.SysUserRoleInfoMapper;
import com.dream.seata.user.server.model.*;
import com.dream.seata.user.server.service.SysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Autowired
    public SysUserInfoServiceImpl(SysUserInfoMapper sysUserInfoMapper,
                                  SysUserRoleInfoMapper sysUserRoleInfoMapper,
                                  SysUserAmountInfoMapper sysUserAmountInfoMapper) {
        this.sysUserInfoMapper = sysUserInfoMapper;
        this.sysUserRoleInfoMapper = sysUserRoleInfoMapper;
        this.sysUserAmountInfoMapper = sysUserAmountInfoMapper;
    }

    private final SysUserInfoMapper sysUserInfoMapper;
    private final SysUserRoleInfoMapper sysUserRoleInfoMapper;
    private final SysUserAmountInfoMapper sysUserAmountInfoMapper;

    @Override
    public UserInfoOutPut loadUserByUsername(String username) {
        SysUserInfoExample userInfoExample = new SysUserInfoExample();
        userInfoExample.createCriteria()
                .andUsernameEqualTo(username)
                .andStatusEqualTo(UserInfoStatus.EFFECTIVE.getKey())
                .andDeletedEqualTo(UserInfoStatus.EFFECTIVE.getKey());
        List<SysUserInfo> userInfoList = sysUserInfoMapper.selectByExample(userInfoExample);
        UserInfoOutPut userInfoOutPut = new UserInfoOutPut();
        if (userInfoList == null || userInfoList.isEmpty()) {
            log.warn("[transaction-user-server]-根据用户名：{}未获取到用户信息", username);
            return userInfoOutPut;
        }
        SysUserInfo sysUserInfo = userInfoList.get(0);
        BeanUtils.copyProperties(sysUserInfo, userInfoOutPut);
        Integer id = sysUserInfo.getId();
        SysUserRoleInfoExample example = new SysUserRoleInfoExample();
        example.createCriteria().andUserIdEqualTo(id);
        List<SysUserRoleInfo> roleInfoList = sysUserRoleInfoMapper.selectByExample(example);
        List<Integer> roleList = new ArrayList<>();
        Optional.ofNullable(roleInfoList).orElse(new ArrayList<>()).stream().forEach(i -> {
            roleList.add(i.getRoleId());
        });
        userInfoOutPut.setRoleList(roleList);
        return userInfoOutPut;
    }

    @Override
    public UserInfoOutPut loginUserInfoDetails(int userId) {
        SysUserInfoExample userInfoExample = new SysUserInfoExample();
        userInfoExample.createCriteria()
                .andIdEqualTo(userId)
                .andStatusEqualTo(UserInfoStatus.EFFECTIVE.getKey())
                .andDeletedEqualTo(UserInfoStatus.EFFECTIVE.getKey());
        List<SysUserInfo> userInfoList = sysUserInfoMapper.selectByExample(userInfoExample);
        UserInfoOutPut userInfoOutPut = new UserInfoOutPut();
        if (userInfoList == null || userInfoList.isEmpty()) {
            log.warn("[transaction-user-server]-根据用户ID：{}未获取到用户信息", userId);
            return userInfoOutPut;
        }
        SysUserInfo sysUserInfo = userInfoList.get(0);
        BeanUtils.copyProperties(sysUserInfo, userInfoOutPut);
        return userInfoOutPut;
    }

    @Override
    public UserInfoAmountOutPut userInfoAmountDetailsByUid(String userUid) {
        SysUserAmountInfoExample example = new SysUserAmountInfoExample();
        example.createCriteria().andUserUidEqualTo(userUid);
        List<SysUserAmountInfo> infoList = sysUserAmountInfoMapper.selectByExample(example);
        UserInfoAmountOutPut userInfoAmountOutPut = new UserInfoAmountOutPut();
        if (infoList == null || infoList.isEmpty()) {
            return userInfoAmountOutPut;
        }
        SysUserAmountInfo sysUserAmountInfo = infoList.get(0);
        BeanUtils.copyProperties(sysUserAmountInfo, userInfoAmountOutPut);
        return userInfoAmountOutPut;
    }

    @Override
    public Result userInfoSettlement(String userUid, BigDecimal deductionAmount) {
        SysUserAmountInfoExample selectExample = new SysUserAmountInfoExample();
        selectExample.createCriteria().andUserUidEqualTo(userUid);
        List<SysUserAmountInfo> infoList = sysUserAmountInfoMapper.selectByExample(selectExample);
        if (infoList == null || infoList.isEmpty()) {
            log.warn("[订单结算扣减账户金额]-根据用户UID:{}未获取到用户信息", userUid);
            return Result.custom(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        SysUserAmountInfo sysUserAmountInfo = infoList.get(0);
        BigDecimal mainAmount = sysUserAmountInfo.getMainAmount();
        if (deductionAmount.compareTo(mainAmount) > 0) {
            log.warn("[订单结算扣减账户金额]-用户UID：{}的余额：{}不足，订单金额:{}", userUid, mainAmount, deductionAmount);
            return Result.custom(ResultCode.USER_ACCOUNT_INSUFFICIENT_BALANCE);
        }
        Integer version = sysUserAmountInfo.getVersion();
        BigDecimal amount = mainAmount.subtract(deductionAmount);
        SysUserAmountInfo updateUserAmountInfo = new SysUserAmountInfo();
        updateUserAmountInfo.setMainAmount(amount);
        updateUserAmountInfo.setVersion(version + 1);
        SysUserAmountInfoExample updateExample = new SysUserAmountInfoExample();
        updateExample.createCriteria()
                .andUserUidEqualTo(userUid)
                .andMainAmountEqualTo(mainAmount)
                .andVersionEqualTo(version);
        int count = sysUserAmountInfoMapper.updateByExampleSelective(updateUserAmountInfo, updateExample);
        if (count > 0) {
            return Result.custom(ResultCode.SUCCESS);
        }
        return Result.custom(ResultCode.USER_ACCOUNT_UPDATE_ERROR);
    }

}
