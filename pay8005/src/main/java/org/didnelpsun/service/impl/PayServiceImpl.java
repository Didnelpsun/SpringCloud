// PayServiceImpl.java
package org.didnelpsun.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.dao.IPayDao;
import org.didnelpsun.service.IPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.didnelpsun.util.Code;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PayServiceImpl implements IPayService {
    @Value("${server.port}")
    private String port;
    private static final String timeout = "3000";
    @Resource
    private IPayDao payDao;

    @Override
    // 指定降级方法并配置超时时间
    @HystrixCommand(fallbackMethod = "handlerPay", commandProperties = {
            // 超时时间
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = PayServiceImpl.timeout),
            // 是否开启断路器
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
            // 请求次数，即一个滚动窗口中打开断路器的最少请求数
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "10"),
            // 时间窗口期，即熔断多长时间后开始尝试恢复
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "10000"),
            // 错误百分比到达多少后熔断
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "60")})
    public Result<Pay> select(Long id) {
        Pay result = payDao.select(id);
        log.info("查询结果:" + result);
        if (result != null) {
            return new Result<>(Code.SUCCESS, "port:" + this.port + ":success", result);
        } else {
            return new Result<>(Code.NO_CONTENT, "port:" + this.port + ":no content", null);
        }
    }

    @Override
    @HystrixCommand(fallbackMethod = "handlerList", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = PayServiceImpl.timeout)})
    public Result<List<Pay>> selects() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Pay> result = payDao.selects();
        log.info("查询结果:" + result);
        if (result.size() > 0) {
            return new Result<>(Code.SUCCESS, "port:" + this.port + ":success", result);
        } else {
            return new Result<>(Code.NO_CONTENT, "port:" + this.port + ":no content", result);
        }
    }

    @Override
    @HystrixCommand(fallbackMethod = "handlerInt", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = PayServiceImpl.timeout)})
    public Result<Integer> insert(Pay pay) {
        int result = payDao.insert(pay);
        log.info("添加结果:" + result);
        if (result > 0) {
            return new Result<>(Code.SUCCESS, "port:" + this.port + ":success", result);
        } else {
            return new Result<>(Code.NO_RESPONSE, "port:" + this.port + ":insert fail", result);
        }
    }

    @Override
    @HystrixCommand(fallbackMethod = "handlerInt", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = PayServiceImpl.timeout)})
    public Result<Integer> update(Pay pay) {
        int result = payDao.update(pay);
        log.info("更新结果:" + result);
        if (result > 0) {
            return new Result<>(Code.SUCCESS, "port:" + this.port + ":success", result);
        } else {
            return new Result<>(Code.NO_RESPONSE, "port:" + this.port + ":update fail", result);
        }
    }

    @Override
    @HystrixCommand(fallbackMethod = "handlerInt", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = PayServiceImpl.timeout)})
    public Result<Integer> delete(Long id) {
        Integer result = payDao.delete(id);
        log.info("删除结果:" + result);
        if (result > 0) {
            return new Result<>(Code.SUCCESS, "port:" + this.port + ":success", result);
        } else {
            return new Result<>(Code.NO_RESPONSE, "port:" + this.port + ":delete fail", result);
        }
    }

    public Result<List<Pay>> handlerList() {
        return new Result<>(Code.SERVER_ERROR, "The server cannot provide services, please try again later", new ArrayList<>() {
        });
    }

    public Result<Pay> handlerPay(Long id) {
        return new Result<>(Code.SERVER_ERROR, "The server cannot provide services, please try again later", new Pay(id, ""));
    }

    public Result<Integer> handlerInt(Pay pay) {
        return new Result<>(Code.SERVER_ERROR, "The server cannot provide services, please try again later", 0);
    }

    public Result<Integer> handlerInt(Long id) {
        return new Result<>(Code.SERVER_ERROR, "The server cannot provide services, please try again later", 0);
    }
}
