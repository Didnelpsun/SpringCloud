// PayApplication.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableEurekaClient
public class PayApplication {
    public static void main(String[] args) {
        try{
            SpringApplication.run(PayApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
