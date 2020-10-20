package com.dream.seata.user.server.service;

import com.dream.seata.user.api.output.SysResourceOutPut;

import java.util.List;

/**
 * @author Lv.QingYu
 */
public interface SysResourceService {

    /**
     * 获取系统资源集合
     * @return
     */
    List<SysResourceOutPut> findSysResource();

}
