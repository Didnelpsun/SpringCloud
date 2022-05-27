// PayFallbackService.java
package org.didnelpsun.service.impl;

import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.IPayService;
import org.didnelpsun.util.Code;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayFallbackService implements IPayService {
    // 静态异常结果
    public static String message = "The server is temporarily unavailable, input: ";
    public static Code code = Code.SERVICE_UNAVAILABLE;

    @Override
    public Result<Pay> select(Long id) {
        return new Result<>(code, message + id, new Pay());
    }

    @Override
    public Result<List<Pay>> selects() {
        return new Result<>(code, message + "null", new ArrayList<>());
    }

    @Override
    public Result<Integer> insert(Pay pay) {
        return new Result<>(code, message + pay, 0);
    }

    @Override
    public Result<Integer> update(Pay pay) {
        return new Result<>(code, message + pay, 0);
    }

    @Override
    public Result<Integer> delete(Long id) {
        return new Result<>(code, message + id, 0);
    }
}
