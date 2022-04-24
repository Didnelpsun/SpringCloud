// PayFallbackService.java
package org.didnelpsun.service.impl;

import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.IPayService;
import org.didnelpsun.util.Code;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PayFallbackService implements IPayService {
    @Override
    public Result<Pay> select(Long id) {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the program", new Pay(id, ""));
    }

    @Override
    public Result<List<Pay>> selects() {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the program", new ArrayList<>() {
        });
    }

    @Override
    public Result<Integer> insert(Pay pay) {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the program", 0);
    }

    @Override
    public Result<Integer> update(Pay pay) {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the program", 0);
    }

    @Override
    public Result<Integer> delete(Long id) {
        return new Result<>(Code.NO_CONTENT, "The client cannot receive services, please check the program", 0);
    }
}
