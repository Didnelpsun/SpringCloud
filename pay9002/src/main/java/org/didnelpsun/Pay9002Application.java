// Pay9002Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {GsonAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class Pay9002Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Pay9002Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}