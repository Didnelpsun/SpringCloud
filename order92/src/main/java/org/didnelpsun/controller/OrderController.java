// OrderController.java
package org.didnelpsun.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@Data
@Slf4j
public class OrderController {
    private String baseUrl;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostConstruct
    public void setBaseUrl() {
        this.baseUrl = "http://PAY/pay";
    }

    @GetMapping()
    public Result<?> selects() {
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
        return restTemplate.exchange(baseUrl, HttpMethod.PUT, new HttpEntity<>(pay, new HttpHeaders()), Result.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, null, Result.class);
    }

    // 查看已经注入的微服务名称列表
    @GetMapping("/discovery")
    public List<String> discoveries() {
        return discoveryClient.getServices();
    }

    // 根据微服务名称即ID查找所有微服务实例
    @GetMapping("/discovery/{id}")
    public List<ServiceInstance> discovery(@PathVariable String id) {
        return discoveryClient.getInstances(id);
    }
}
