// DefaultHandler.java
package org.didnelpsun.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.util.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DefaultHandler {
    // 静态流控结果
    public static String message = "Access restrictions, input: ";
    public static Code code = Code.FORBIDDEN;

    public static Result<List<Pay>> selectsHandler(BlockException exception) {
        exception.printStackTrace();
        return new Result<>(code, message + "null", new ArrayList<>());
    }

    public static Result<Pay> selectHandler(Long id, BlockException exception) {
        exception.printStackTrace();
        return new Result<>(code, message + id, new Pay());

    }
    public static Result<Integer> insertHandler(Pay pay, BlockException exception) {
        exception.printStackTrace();
        return new Result<>(code, message + pay, 0);
    }

    public static ResponseEntity<Result<Integer>> updateHandler(Pay pay, BlockException exception) {
        exception.printStackTrace();
        Result<Integer> result = new Result<>(code, message + pay, 0);
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity<Result<Integer>> deleteHandler(Long id, BlockException exception) {
        exception.printStackTrace();
        Result<Integer> result = new Result<>(code, message + id, 0);
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }
}
