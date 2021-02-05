package com.dream.sharding.sphere.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lv.QingYu
 */
@Data
@EqualsAndHashCode
public class UserInfoDTO {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
