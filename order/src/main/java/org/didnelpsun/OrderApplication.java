// OrderApplication.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableEurekaClient
public class OrderApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(OrderApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
