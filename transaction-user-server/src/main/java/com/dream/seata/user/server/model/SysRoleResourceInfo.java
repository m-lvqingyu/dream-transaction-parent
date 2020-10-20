package com.dream.seata.user.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * sys_role_resource_info
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleResourceInfo {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private Integer resourceId;

}
