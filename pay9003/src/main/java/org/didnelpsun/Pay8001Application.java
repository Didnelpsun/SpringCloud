// Pay8001Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
public class Pay8001Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Pay8001Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
