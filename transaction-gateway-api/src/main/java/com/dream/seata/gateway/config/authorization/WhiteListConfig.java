package com.dream.seata.gateway.config.authorization;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Lv.QingYu
 * @description 白名单（无需权限校验的url）
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.whitelist")
@EnableConfigurationProperties(WhiteListConfig.class)
public class WhiteListConfig {

    private List<String> urls;
}
