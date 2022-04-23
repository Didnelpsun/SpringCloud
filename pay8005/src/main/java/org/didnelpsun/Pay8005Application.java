// Pay8005Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
public class Pay8005Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Pay8005Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
