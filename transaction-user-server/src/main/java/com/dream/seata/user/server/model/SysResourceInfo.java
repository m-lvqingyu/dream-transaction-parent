package com.dream.seata.user.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * sys_resource_info
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysResourceInfo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}
