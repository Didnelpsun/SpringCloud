// Order82Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Order82Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Order82Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
