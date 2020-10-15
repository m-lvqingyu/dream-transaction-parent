package com.dream.seata.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Lv.QingYu
 *
 * @descripton 无需权限验证的URL
 */
@Configuration
@ConfigurationProperties(prefix = "server.security.ignore")
public class IgnoreUrlsConfig {

    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
