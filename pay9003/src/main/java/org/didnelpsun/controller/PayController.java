package org.didnelpsun.controller;

import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.IPayService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private IPayService payService;
    // 注意这里有两个实现，有netflix和cloud的
    // 要从cloud包中导入，因为netflix的没用getServices方法也无法自动注入
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public Result<Pay> select(@PathVariable Long id) {
        return payService.select(id);
    }

    @GetMapping()
    public Result<List<Pay>> selects() {
        log.info("查询开始");
        return payService.selects();
    }

    @PostMapping()
    public Result<Integer> insert(@RequestBody Pay pay) {
        return payService.insert(pay);
    }

    @PutMapping()
    public Result<Integer> update(@RequestBody Pay pay) {
        return payService.update(pay);
    }

    @DeleteMapping("/{id}")
    public Result<Integer> delete(@PathVariable long id) {
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
}
