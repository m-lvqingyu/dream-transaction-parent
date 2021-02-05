package com.dream.sharding.sphere.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Lv.QingYu
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo {

    private Long id;

    /**
     * 用户唯一ID
     */
    private String uid;

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

    private Date createTime;

    private Date updateTime;

}
