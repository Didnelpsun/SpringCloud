package org.didnelpsun.controller;

import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.IPayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/pay")
public class PayController {
    @Resource
    private IPayService payService;

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
}
