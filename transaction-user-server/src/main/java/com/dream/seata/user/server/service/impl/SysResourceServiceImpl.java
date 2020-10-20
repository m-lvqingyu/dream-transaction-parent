package com.dream.seata.user.server.service.impl;

import com.dream.seata.user.api.output.SysResourceOutPut;
import com.dream.seata.user.server.mapper.SysResourceInfoMapper;
import com.dream.seata.user.server.mapper.SysRoleResourceInfoMapper;
import com.dream.seata.user.server.model.SysResourceInfo;
import com.dream.seata.user.server.model.SysResourceInfoExample;
import com.dream.seata.user.server.model.SysRoleResourceInfo;
import com.dream.seata.user.server.model.SysRoleResourceInfoExample;
import com.dream.seata.user.server.service.SysResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lv.QingYu
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceInfoMapper sysResourceInfoMapper;
    @Autowired
    private SysRoleResourceInfoMapper sysRoleResourceInfoMapper;

    @Override
    public List<SysResourceOutPut> findSysResource() {
        List<SysResourceOutPut> sysResourceOutPutList = new ArrayList<>();
        SysResourceInfoExample sysResourceExample = new SysResourceInfoExample();
        List<SysResourceInfo> sysResourceList = sysResourceInfoMapper.selectByExample(sysResourceExample);
        if(sysResourceList == null || sysResourceList.isEmpty()){
            return sysResourceOutPutList;
        }
        for(SysResourceInfo sysResource : sysResourceList){
            Integer id = sysResource.getId();
            SysRoleResourceInfoExample sysRoleResourceExample = new SysRoleResourceInfoExample();
            sysRoleResourceExample.createCriteria().andResourceIdEqualTo(id);
            List<SysRoleResourceInfo> roleResourceList = sysRoleResourceInfoMapper.selectByExample(sysRoleResourceExample);
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
