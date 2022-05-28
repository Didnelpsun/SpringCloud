// OrderController.java
package org.didnelpsun.controller;

import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.entity.Order;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Resource
    private OrderServiceImpl service;

    @GetMapping("/{id}")
    public Result<Order> select(@PathVariable Long id) {
        return service.select(id);
    }

    @GetMapping()
    public Result<List<Order>> selects() {
        return service.selects();
    }

    @PostMapping()
    public Result<Integer> insert(@RequestBody Order order) {
        return service.insert(order);
    }

    @PutMapping()
    public Result<Integer> update(@RequestBody Order order) {
        return service.update(order);
    }

    @DeleteMapping("/{id}")
    public Result<Integer> delete(@PathVariable long id) {
        return service.delete(id);
    }
}
