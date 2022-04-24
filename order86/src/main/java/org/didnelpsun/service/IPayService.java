// IPayService.java
package org.didnelpsun.service;

import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.impl.PayFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
// 找ServiceId为pay的微服务
@FeignClient(value = "PAY", fallback = PayFallbackService.class)
public interface IPayService {
    String prefix = "/pay";

    // 客户端可以调用到pay模块服务的接口清单
    // @RestMapping注解表示要调用这些接口方法时Feign所自动请求的地址
    // 方法为客户端定义的服务层接口所提供的服务，没有业务逻辑，只起到根据控制层传输的参数调用路径请求服务端服务的作用
    // 之前需要通过RestTemplate来调用服务端API，此时通过OpenFeign可以直接调用服务API，由OpenFeign负责将API与参数进行封装传输到服务端，再返回数据
    @GetMapping(prefix + "/{id}")
    Result<Pay> select(@PathVariable(value = "id") Long id);

    @GetMapping(prefix)
    Result<List<Pay>> selects();

    @PostMapping(prefix)
    Result<Integer> insert(Pay pay);

    @PutMapping(prefix)
    Result<Integer> update(Pay pay);

    @DeleteMapping(prefix + "/{id}")
    Result<Integer> delete(@PathVariable(value = "id") Long id);
}
