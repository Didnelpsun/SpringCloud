// Pay8003Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Pay8003Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Pay8003Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
