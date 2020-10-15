package com.dream.seata.auth.server.service.impl;

import com.dream.seata.auth.api.output.SysResourceOutPut;
import com.dream.seata.auth.server.mapper.TSysResourceMapper;
import com.dream.seata.auth.server.mapper.TSysRoleResourceMapper;
import com.dream.seata.auth.server.model.TSysResource;
import com.dream.seata.auth.server.model.TSysResourceExample;
import com.dream.seata.auth.server.model.TSysRoleResource;
import com.dream.seata.auth.server.model.TSysRoleResourceExample;
import com.dream.seata.auth.server.service.TSysResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lv.QingYu
 */
@Service
public class TSysResourceServiceImpl implements TSysResourceService {

    @Autowired
    private TSysResourceMapper tSysResourceMapper;
    @Autowired
    private TSysRoleResourceMapper tSysRoleResourceMapper;

    @Override
    public List<SysResourceOutPut> findSysResource() {
        List<SysResourceOutPut> sysResourceOutPutList = new ArrayList<>();
        TSysResourceExample sysResourceExample = new TSysResourceExample();
        List<TSysResource> sysResourceList = tSysResourceMapper.selectByExample(sysResourceExample);
        if(sysResourceList == null || sysResourceList.isEmpty()){
            return sysResourceOutPutList;
        }
        TSysRoleResourceExample sysRoleResourceExample = new TSysRoleResourceExample();
        TSysRoleResourceExample.Criteria criteria = sysRoleResourceExample.createCriteria();
        for(TSysResource sysResource : sysResourceList){
            Integer id = sysResource.getId();
            criteria.andResourceIdEqualTo(id);
            List<TSysRoleResource> roleResourceList = tSysRoleResourceMapper.selectByExample(sysRoleResourceExample);
            SysResourceOutPut sysResourceOutPut = new SysResourceOutPut();
            BeanUtils.copyProperties(sysResource, sysResourceOutPut);
            List<Integer> roleIdList = new ArrayList<>();
            if(roleResourceList != null || !roleResourceList.isEmpty()){
                roleResourceList.stream().forEach(i -> {
                    roleIdList.add(i.getRoleId());
                });
            }
            sysResourceOutPut.setRoleIdList(roleIdList);
            sysResourceOutPutList.add(sysResourceOutPut);
        }
        return sysResourceOutPutList;
    }

}
