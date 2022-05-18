// PayController.java
package org.didnelpsun.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PayController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/{id}")
    public String getPay(@PathVariable Integer id){
        log.info(this.port + ":" + id);
        return this.port + ":" + id;
    }
}
