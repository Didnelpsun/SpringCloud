package org.didnelpsun.service;

import org.didnelpsun.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("account")
public interface IAccountService {
    @PostMapping("/account/decrease")
    Result<Integer> decrease(@RequestParam("id") Long id, @RequestParam("money") BigDecimal money);
}
