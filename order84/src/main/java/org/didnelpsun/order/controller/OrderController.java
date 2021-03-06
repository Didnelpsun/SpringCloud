// OrderController.java
package org.didnelpsun.order.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.order.loadbalancer.LoadBalancer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@Configuration
@ConfigurationProperties(prefix = "remote")
@Data
@Slf4j
public class OrderController {
    private String baseUrl;
    private String serviceId = "pay";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancer loadBalancer;

    public String getBaseUrl() {
        return getBaseUrl(this.serviceId);
    }

    public String getBaseUrl(String ServiceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(ServiceId);
        if (instances == null || instances.size() == 0)
            return null;
        ServiceInstance serviceInstance = loadBalancer.getInstance(instances);
        return serviceInstance.getUri() + "/" + this.serviceId;
    }

    @GetMapping()
    public Result<?> selects() {
        return restTemplate.getForObject(this.getBaseUrl(), Result.class);
    }

    @GetMapping("/{id}")
    public Result<?> select(@PathVariable Long id) {
        return restTemplate.getForObject(this.getBaseUrl() + "/" + id, Result.class);
    }

    @PostMapping()
    public Result<?> insert(Pay pay) {
        return restTemplate.postForObject(this.getBaseUrl(), pay, Result.class);
    }

    // ???????????????spring???RestTemplate?????????,????????????delete???????????????????????????
    // ????????????exchange?????????,?????????????????????????????????

    @PutMapping()
    public ResponseEntity<Result> update(Pay pay) {
        return restTemplate.exchange(this.getBaseUrl(), HttpMethod.PUT, new HttpEntity<>(pay, new HttpHeaders()), Result.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return restTemplate.exchange(this.getBaseUrl() + "/" + id, HttpMethod.DELETE, null, Result.class);
    }

    // ??????????????????????????????????????????
    @GetMapping("/discovery")
    public List<String> discoveries() {
        return discoveryClient.getServices();
    }

    // ????????????????????????ID???????????????????????????
    @GetMapping("/discovery/{id}")
    public List<ServiceInstance> discovery(@PathVariable String id) {
        return discoveryClient.getInstances(id);
    }
}
