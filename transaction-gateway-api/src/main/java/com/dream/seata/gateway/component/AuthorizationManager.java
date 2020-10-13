package com.dream.seata.gateway.component;

import cn.hutool.core.convert.Convert;
import com.dream.seata.core.constant.AuthConstants;
import com.dream.seata.gateway.config.authorization.WhiteListConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * @description 鉴权管理器:用于判断是否有资源的访问权限
 *
 * @author Lv.QingYu
 */
@Slf4j
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private WhiteListConfig whiteListConfig;
    @Autowired
    private RedissonClient redissonClient;

    private static final AuthorizationDecision SUCCESS = new AuthorizationDecision(true);
    private static final AuthorizationDecision FAIL = new AuthorizationDecision(false);

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest httpRequest = authorizationContext.getExchange().getRequest();
        String path = httpRequest.getURI().getPath();
        List<String> ignoreUrls = whiteListConfig.getUrls();
        PathMatcher pathMatcher = new AntPathMatcher();

        // 白名单直接放行,无需权限校验
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, path)) {
                return Mono.just(SUCCESS);
            }
        }

        // 跨域预检查请求直接放行，无需权限校验
        if (httpRequest.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(SUCCESS);
        }

        // 判断Token是否存在，如果不存在则拦截
        String token = httpRequest.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            return Mono.just(FAIL);
        }

        // 获取权限资源
        RBucket<Map<Object, Object>> bucket = redissonClient.getBucket(AuthConstants.RESOURCE_ROLES_KEY);
        Map<Object, Object> resourceRolesMap = bucket.get();
        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();

        // 根据路径，获取该路径可访问的角色集合
        Set<String> authorities = new HashSet<>();
        while(iterator.hasNext()){
            String pattern = (String) iterator.next();
            if(pathMatcher.match(pattern, path)){
                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
            }
        }

        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                // isAuthenticated 当前用户是否已经通过认证（true:是 | false:否）
                .filter(Authentication::isAuthenticated)
                // getAuthorities 返回当前Authentication对象拥有的权限,即当前用户拥有的权限
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId ->  authorities.contains(roleId))
                .map(AuthorizationDecision::new)
                // 如果当前对象为空，设置默认的defaultValue
                .defaultIfEmpty(FAIL);
        return authorizationDecisionMono;
    }


}
