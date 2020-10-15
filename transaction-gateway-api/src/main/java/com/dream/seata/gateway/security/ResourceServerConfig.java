package com.dream.seata.gateway.security;

import cn.hutool.core.util.ArrayUtil;
import com.dream.seata.core.constant.AuthConstants;
import com.dream.seata.gateway.config.IgnoreUrlsConfig;
import com.dream.seata.gateway.security.component.AuthorizationManager;
import com.dream.seata.gateway.security.component.CustomServerAccessDeniedHandler;
import com.dream.seata.gateway.security.component.CustomServerAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Lv.QingYu
 * @description 资源服务器配置
 */
@Component
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private AuthorizationManager authorizationManager;
    @Autowired
    private CustomServerAccessDeniedHandler customServerAccessDeniedHandler;
    @Autowired
    private CustomServerAuthenticationEntryPoint customServerAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
        String[] ignoreUrlArray = ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class);
        log.info("[transaction-gateway-api]-无需经过鉴权的Url：{}", StringUtils.join(ignoreUrlArray, ","));
        httpSecurity.authorizeExchange()
                // 无需经过鉴权校验的URL
                .pathMatchers(ignoreUrlArray).permitAll()
                // 鉴权管理器配置
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling()
                // 处理未授权|未认证逻辑
                .accessDeniedHandler(customServerAccessDeniedHandler)
                .authenticationEntryPoint(customServerAuthenticationEntryPoint)
                .and().csrf().disable();

        return httpSecurity.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
