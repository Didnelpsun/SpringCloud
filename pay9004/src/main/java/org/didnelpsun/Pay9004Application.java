// Pay9004Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableDiscoveryClient
public class Pay9004Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Pay9004Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
