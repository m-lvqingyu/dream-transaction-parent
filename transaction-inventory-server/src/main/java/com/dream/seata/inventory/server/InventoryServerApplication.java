package com.dream.seata.inventory.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Lv.QingYu
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.dream.seata.inventory.server.dao"})
public class InventoryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServerApplication.class, args);
    }

}
