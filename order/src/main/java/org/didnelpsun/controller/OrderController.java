// OrderController.java
package org.didnelpsun.controller;

import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    public Result<Integer> insert(Pay pay){
        return restTemplate.
    }
}
