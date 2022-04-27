// ClientController.java
package org.didnelpsun.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ClientController {
    @Value("${test.info}")
    private String info;

    @GetMapping("/info")
    public String getInfo(){
        return this.info;
    }
}
