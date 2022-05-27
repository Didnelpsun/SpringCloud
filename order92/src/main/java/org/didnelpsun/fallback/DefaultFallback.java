// DefaultFallback.java
package org.didnelpsun.fallback;

import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.util.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DefaultFallback {
    // 静态异常结果
    public static String message = "Parameter exception, input: ";
    public static Code code = Code.BAD_REQUEST;

    public static Result<List<Pay>> selectsFallback(Throwable exception) {
        exception.printStackTrace();
        return new Result<>(code, message + "null", new ArrayList<>());
    }

    public static Result<Pay> selectFallback(Long id, Throwable exception) {
        exception.printStackTrace();
        return new Result<>(code, message + id, new Pay());

    }

    public static Result<Integer> insertFallback(Pay pay, Throwable exception) {
        exception.printStackTrace();
        return new Result<>(code, message + pay, 0);
    }

    public static ResponseEntity<Result<Integer>> updateFallback(Pay pay, Throwable exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(new Result<>(code, message + pay, 0), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Result<Integer>> deleteFallback(Long id, Throwable exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(new Result<>(code, message + id, 0), HttpStatus.BAD_REQUEST);
    }
}
