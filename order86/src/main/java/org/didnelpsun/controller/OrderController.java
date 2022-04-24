// OrderController.java
package org.didnelpsun.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.IPayService;
import org.didnelpsun.util.Code;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
//@DefaultProperties(defaultFallback="handler")
@Slf4j
public class OrderController {
    private static final String timeout = "3000";
    @Resource
    private IPayService payService;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping()
    @HystrixCommand(fallbackMethod = "handlerList", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = OrderController.timeout)})
    public Result<List<Pay>> selects() {
        return payService.selects();
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "handlerPay", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = OrderController.timeout)})
    public Result<Pay> select(@PathVariable Long id) {
        return payService.select(id);
    }

    @PostMapping()
    @HystrixCommand(fallbackMethod = "handlerInt", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = OrderController.timeout)})
    public Result<Integer> insert(Pay pay) {
        return payService.insert(pay);
    }

    @PutMapping()
    @HystrixCommand(fallbackMethod = "handlerInt", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = OrderController.timeout)})
    public Result<Integer> update(Pay pay) {
        return payService.update(pay);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "handlerInt", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = OrderController.timeout)})
    public Result<Integer> delete(@PathVariable Long id) {
        return payService.delete(id);
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

    public Result<?> handler(){
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the time limit or program");
    }

    public Result<List<Pay>> handlerList() {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the time limit or program", new ArrayList<>() {
        });
    }

    public Result<Pay> handlerPay(Long id) {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the time limit or program", new Pay(id, ""));
    }

    public Result<Integer> handlerInt(Pay pay) {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the time limit or program", 0);
    }

    public Result<Integer> handlerInt(Long id) {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the time limit or program", 0);
    }
}
