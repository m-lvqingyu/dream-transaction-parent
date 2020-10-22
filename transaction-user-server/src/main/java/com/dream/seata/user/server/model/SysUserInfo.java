package com.dream.seata.user.server.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 用户信息表
 */
@Data
public class SysUserInfo {
    private Integer id;

    /**
     * 用户唯一Id
     */
    private String userUid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 删除标识（0未删除 1已删除）
     */
    private Integer deleted;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 用户状态（0正常 1禁用）
     */
    private Integer status;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新用户
     */
    private String updateBy;

}