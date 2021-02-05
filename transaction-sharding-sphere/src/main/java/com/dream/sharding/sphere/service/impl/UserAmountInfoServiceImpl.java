package com.dream.sharding.sphere.service.impl;

import com.dream.sharding.sphere.convert.UserAmountInfoConvert;
import com.dream.sharding.sphere.entity.UserAmountInfo;
import com.dream.sharding.sphere.mapper.UserAmountInfoMapper;
import com.dream.sharding.sphere.service.IUserAmountInfoService;
import com.dream.sharding.sphere.vo.UserAmountInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lv.QingYu
 * @since 2021-02-01
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAmountInfoServiceImpl implements IUserAmountInfoService {

    private final UserAmountInfoMapper userAmountInfoMapper;
    private final UserAmountInfoConvert userAmountInfoConvert;

    @Override
    public UserAmountInfoVO getUserAmountById(Long id) {
        UserAmountInfo info = userAmountInfoMapper.getUserAmountInfoById(id);
        if (info == null) {
            log.warn("[查询账户信息]-根据ID:{}未获取到用户账户信息", id);
            return null;
        }
        UserAmountInfoVO amountInfoVO = userAmountInfoConvert.userAmountConvert(info);
        return amountInfoVO;
    }
}
