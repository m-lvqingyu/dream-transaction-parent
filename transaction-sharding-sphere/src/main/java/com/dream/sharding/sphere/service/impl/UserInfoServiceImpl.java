package com.dream.sharding.sphere.service.impl;

import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.seata.core.utils.ServerUidUtils;
import com.dream.sharding.sphere.convert.UserInfoConvert;
import com.dream.sharding.sphere.dto.UserInfoDTO;
import com.dream.sharding.sphere.entity.UserInfo;
import com.dream.sharding.sphere.mapper.UserAmountInfoMapper;
import com.dream.sharding.sphere.mapper.UserInfoMapper;
import com.dream.sharding.sphere.service.IUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lv.QingYu
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoServiceImpl implements IUserInfoService {

    private final UserInfoConvert userInfoConvert;
    private final UserInfoMapper userInfoMapper;
    private final UserAmountInfoMapper userAmountInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveUser(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = userInfoConvert.convert(userInfoDTO);
        String uid = ServerUidUtils.findUid(2, 2);
        userInfo.setUid(uid);
        Long userId = userInfoMapper.insertUserInfo(userInfo);
        if (userId == null || userId <= 0) {
            return Result.custom(ResultCode.SYSTEM_RESOURCE_ACCESS_ERROR);
        }
        Long amountId = userAmountInfoMapper.insertUserAmount(uid, userInfo.getCreateTime());
        if (amountId == null || amountId <= 0) {
            throw new RuntimeException("新增账户失败");
        }
        return Result.success(uid);
    }

}
