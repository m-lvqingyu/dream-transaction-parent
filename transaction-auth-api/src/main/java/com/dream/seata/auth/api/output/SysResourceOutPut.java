package com.dream.seata.auth.api.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysResourceOutPut {

    /**
     * 资源ID
     */
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 拥有资源权限角色ID集合
     */
    private List<Integer> roleIdList;
}
