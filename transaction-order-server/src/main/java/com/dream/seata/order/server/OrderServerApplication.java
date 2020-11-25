package com.dream.seata.order.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: <pre>
 * </pre>
 * @author: Lvqingyu
 * @create: 2020/10/02 16:33
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.dream.seata.order.server.client", "com.dream.seata.inventory.api"})
@MapperScan(basePackages = {"com.dream.seata.order.server.dao"})
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

}
