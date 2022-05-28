// IStorageService.java
package org.didnelpsun.service;

import org.didnelpsun.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 要远程调用storage服务接口，所以这里指定要调用storage的哪些方法的接口
@FeignClient("storage")
public interface IStorageService {
    // 必须使用注解@RequestParam指定参数，否则会报错Method has too many Body parameters
    @PostMapping("/storage/decrease")
    Result<Integer> decrease(@RequestParam("id") Long id, @RequestParam("count") Integer count);
}
