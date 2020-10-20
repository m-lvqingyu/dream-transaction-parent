package com.dream.seata.user.server.component;

import cn.hutool.core.collection.CollectionUtil;
import com.dream.seata.core.constant.AuthConstants;
import com.dream.seata.user.api.output.SysResourceOutPut;
import com.dream.seata.user.server.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
public class InitResourceRolesCacheRunner implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public void run(String... args) throws Exception {
        List<SysResourceOutPut> resourceOutPutList = sysResourceService.findSysResource();
        log.info("[transaction-auth-server]-初始化资源角色对应关系信息：{}", resourceOutPutList);
        RBucket<Map<Object, Object>> bucket = redissonClient.getBucket(AuthConstants.RESOURCE_ROLES_KEY);
        bucket.delete();
        Map<Object, Object> resourceRolesMap = new TreeMap<>();
        Optional.ofNullable(resourceOutPutList).orElse(new ArrayList<>()).forEach(resourceOutPut -> {
            List<String> roles = Optional.ofNullable(resourceOutPut.getRoleIdList()).orElse(new ArrayList<>())
                    .stream().map(roleId -> AuthConstants.AUTHORITY_PREFIX + roleId)
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(roles)) {
                resourceRolesMap.put(resourceOutPut.getUrl(), roles);
            }
        });
        bucket.set(resourceRolesMap);
        log.info("[transaction-auth-server]-初始化资源角色对应关系信息完毕");
    }
}
