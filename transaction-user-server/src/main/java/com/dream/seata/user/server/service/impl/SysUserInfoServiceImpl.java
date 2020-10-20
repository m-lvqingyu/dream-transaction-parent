package com.dream.seata.user.server.service.impl;

import com.dream.seata.core.enums.UserInfoStatus;
import com.dream.seata.user.api.output.UserInfoOutPut;
import com.dream.seata.user.server.mapper.SysUserInfoMapper;
import com.dream.seata.user.server.mapper.SysUserRoleInfoMapper;
import com.dream.seata.user.server.model.SysUserInfo;
import com.dream.seata.user.server.model.SysUserInfoExample;
import com.dream.seata.user.server.model.SysUserRoleInfo;
import com.dream.seata.user.server.model.SysUserRoleInfoExample;
import com.dream.seata.user.server.service.SysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SysUserInfoMapper sysUserInfoMapper;
    @Autowired
    private SysUserRoleInfoMapper sysUserRoleInfoMapper;

    @Override
    public UserInfoOutPut loadUserByUsername(String username) {
        SysUserInfoExample userInfoExample = new SysUserInfoExample();
        userInfoExample.createCriteria()
                .andUsernameEqualTo(username)
                .andStatusEqualTo(UserInfoStatus.EFFECTIVE.getKey())
                .andDeletedEqualTo(UserInfoStatus.EFFECTIVE.getKey());
        List<SysUserInfo> userInfoList = sysUserInfoMapper.selectByExample(userInfoExample);
        UserInfoOutPut userInfoOutPut = new UserInfoOutPut();
        if(userInfoList == null || userInfoList.isEmpty()){
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
        Optional.ofNullable(roleInfoList).orElse(new ArrayList<>()).stream().forEach(i ->{
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
        if(userInfoList == null || userInfoList.isEmpty()){
            log.warn("[transaction-user-server]-根据用户ID：{}未获取到用户信息", userId);
            return userInfoOutPut;
        }
        SysUserInfo sysUserInfo = userInfoList.get(0);
        BeanUtils.copyProperties(sysUserInfo, userInfoOutPut);
        return userInfoOutPut;
    }

}
