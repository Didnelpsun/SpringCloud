// ClientController.java
package org.didnelpsun.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 开启Nacos的动态刷新功能
@RefreshScope
public class ClientController {
    @Value("${config.info}")
    private String info;

    @GetMapping("/config/info")
    public String getInfo(){
        return info;
    }
}
