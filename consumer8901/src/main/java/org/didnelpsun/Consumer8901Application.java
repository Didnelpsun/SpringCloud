// Consumer8901Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableEurekaClient
public class Consumer8901Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Consumer8901Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

