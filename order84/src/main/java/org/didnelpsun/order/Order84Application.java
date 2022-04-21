// Order81Application.java
package org.didnelpsun.order;

import org.didnelpsun.rule.CustomizeRole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
@RibbonClient(name = "pay", configuration = CustomizeRole.class)
public class Order84Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Order84Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
