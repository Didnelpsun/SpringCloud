// Order81Application.java
package org.didnelpsun.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
public class Order84Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Order84Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
