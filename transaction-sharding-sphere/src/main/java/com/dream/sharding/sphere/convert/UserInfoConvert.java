package com.dream.sharding.sphere.convert;

import com.dream.sharding.sphere.dto.UserInfoDTO;
import com.dream.sharding.sphere.entity.UserInfo;
import com.dream.sharding.sphere.from.UserInfoFrom;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lv.QingYu
 */
@Component
public class UserInfoConvert {

    public UserInfo convert(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(userInfoDTO.getNickname());
        userInfo.setUsername(userInfoDTO.getUsername());
        userInfo.setPassword(userInfoDTO.getPassword());
        Date currentTime = new Date();
        userInfo.setCreateTime(currentTime);
        userInfo.setUpdateTime(currentTime);
        return userInfo;
    }

    public UserInfoDTO convert(UserInfoFrom userInfoFrom) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setNickname(userInfoFrom.getNickname());
        userInfoDTO.setUsername(userInfoFrom.getUsername());
        userInfoDTO.setPassword(userInfoFrom.getPassword());
        return userInfoDTO;
    }

}
