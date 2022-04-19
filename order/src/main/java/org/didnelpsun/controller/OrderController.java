// OrderController.java
package org.didnelpsun.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Configuration
@ConfigurationProperties(prefix = "remote")
@Data
@Slf4j
public class OrderController {
    private String url;
    private String port;
    private String baseUrl;
    @Resource
    private RestTemplate restTemplate;

    @PostConstruct
    public void setBaseUrl() {
        this.baseUrl = this.url + ":" + this.port + "/pay";
    }

    @GetMapping()
    public Result<?> selects() {
        log.info(baseUrl);
        return restTemplate.getForObject(baseUrl, Result.class);
    }

    @GetMapping("/{id}")
    public Result<?> select(@PathVariable Long id) {
        return restTemplate.getForObject(baseUrl + "/" + id, Result.class);
    }

    @PostMapping()
    public Result<?> insert(Pay pay) {
        return restTemplate.postForObject(baseUrl, pay, Result.class);
    }

    // 最近在使用spring的RestTemplate的时候,调用他的delete方法发现没有返回值
    // 所以使用exchange来代替,就能得到调用后的返回值

    @PutMapping()
    public ResponseEntity<Result> update(Pay pay) {
        return restTemplate.exchange(baseUrl, HttpMethod.PUT , new HttpEntity<>(pay, new HttpHeaders()),Result.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, null, Result.class);
    }
}
