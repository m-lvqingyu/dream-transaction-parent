package com.dream.seata.auth.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_sys_role_resource
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TSysRoleResource {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private Integer resourceId;
    
}
