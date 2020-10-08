package com.dream.seata.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/08 17:30
 */
@EnableEurekaClient
@SpringBootApplication
public class TransactionGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionGatewayApplication.class, args);
    }

}
