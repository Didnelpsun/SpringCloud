// Client3377Application.java
package org.didnelpsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Client3377Application {
    public static void main(String[] args) {
        SpringApplication.run(Client3377Application.class, args);
    }
}
