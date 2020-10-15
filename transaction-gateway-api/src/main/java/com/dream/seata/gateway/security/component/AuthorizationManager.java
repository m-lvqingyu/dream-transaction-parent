package com.dream.seata.gateway.security.component;

import cn.hutool.core.convert.Convert;
import com.dream.seata.core.constant.AuthConstants;
import com.dream.seata.gateway.config.IgnoreUrlsConfig;
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
 * @author Lv.QingYu
 * @description 鉴权管理器，用于判断是否有资源的访问权限
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    private static final AuthorizationDecision SUCCESS = new AuthorizationDecision(true);
    private static final AuthorizationDecision FAIL = new AuthorizationDecision(false);

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String requestUri = request.getURI().getPath();
        List<String> ignoreUrlsList = ignoreUrlsConfig.getUrls();
        PathMatcher pathMatcher = new AntPathMatcher();

        for (String ignoreUrl : ignoreUrlsList) {
            if (pathMatcher.match(ignoreUrl, requestUri)) {
                return Mono.just(SUCCESS);
            }
        }

        // 跨域的预检请求直接放行
        HttpMethod httpMethod = request.getMethod();
        if (httpMethod == HttpMethod.OPTIONS) {
            return Mono.just(SUCCESS);
        }

        RBucket<Map<Object, Object>> bucket = redissonClient.getBucket(AuthConstants.RESOURCE_ROLES_KEY);
        Map<Object, Object> resourcesRolesMap = bucket.get();
        Iterator<Object> iterator = resourcesRolesMap.keySet().iterator();
        Set<String> authorities = new HashSet<>();
        if (iterator.hasNext()) {
            String path = (String) iterator.next();
            if (pathMatcher.match(path, requestUri)) {
                authorities.addAll(Convert.toList(String.class, resourcesRolesMap.get(path)));
            }
        }

        Mono<AuthorizationDecision> decisionMono = mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId -> authorities.contains(roleId))
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(FAIL);
        return decisionMono;
    }
}
