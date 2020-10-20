package com.dream.seata.user.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sys_user_role_info
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRoleInfo {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;

}
