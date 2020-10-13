package com.dream.seata.gateway.config.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @description 资源服务器配置
 *
 * @author Lv.QingYu
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    /*@Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.oauth2ResourceServer();

    }*/
}
