package com.dream.seata.auth.server.service;

import com.dream.seata.auth.api.output.SysResourceOutPut;

import java.util.List;

public interface TSysResourceService {

    /**
     * 获取系统资源集合
     * @return
     */
    List<SysResourceOutPut> findSysResource();

}
