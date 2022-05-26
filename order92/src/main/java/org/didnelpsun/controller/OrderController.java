// OrderController.java
package org.didnelpsun.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.fallback.DefaultFallback;
import org.didnelpsun.handler.DefaultHandler;
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
        this.baseUrl = "http://pay/pay";
    }

    @GetMapping()
    @SentinelResource(value = "selects", blockHandlerClass = DefaultHandler.class, blockHandler = "selectsHandler", fallbackClass = DefaultFallback.class, fallback = "selectsFallback")
    public Result<?> selects() {
        return restTemplate.getForObject(baseUrl, Result.class);
    }

    @GetMapping("/{id}")
    @SentinelResource(value = "select", blockHandlerClass = DefaultHandler.class, blockHandler = "selectHandler", fallbackClass = DefaultFallback.class, fallback = "selectFallback")
    public Result<?> select(@PathVariable Long id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than 0");
        return restTemplate.getForObject(baseUrl + "/" + id, Result.class);
    }

    @PostMapping()
    @SentinelResource(value = "insert", blockHandlerClass = DefaultHandler.class, blockHandler = "insertHandler", fallbackClass = DefaultFallback.class, fallback = "insertFallback")
    public Result<?> insert(Pay pay) {
        return restTemplate.postForObject(baseUrl, pay, Result.class);
    }

    @PutMapping()
    @SentinelResource(value = "update", blockHandlerClass = DefaultHandler.class, blockHandler = "updateHandler", fallbackClass = DefaultFallback.class, fallback = "updateFallback")
    public ResponseEntity<Result> update(Pay pay) {
        return restTemplate.exchange(baseUrl, HttpMethod.PUT, new HttpEntity<>(pay, new HttpHeaders()), Result.class);
    }

    @DeleteMapping("/{id}")
    @SentinelResource(value = "delete", blockHandlerClass = DefaultHandler.class, blockHandler = "deleteHandler", fallbackClass = DefaultFallback.class, fallback = "deleteFallback")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than 0");
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
