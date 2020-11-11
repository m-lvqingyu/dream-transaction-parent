package com.dream.seata.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/03 14:33
 */

@EnableEurekaServer
@SpringBootApplication
public class RegistryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryServerApplication.class, args);
    }

}
