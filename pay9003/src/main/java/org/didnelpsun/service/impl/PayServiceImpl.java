// PayServiceImpl.java
package org.didnelpsun.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.dao.IPayDao;
import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.IPayService;
import org.didnelpsun.util.Code;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PayServiceImpl implements IPayService {
    @Value("${server.port}")
    private String port;
    @Resource
    private IPayDao payDao;

    @Override
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
    public Result<List<Pay>> selects() {
        List<Pay> result = payDao.selects();
        log.info("查询结果:" + result);
        if (result.size() > 0) {
            return new Result<>(Code.SUCCESS, "port:" + this.port + ":success", result);
        } else {
            return new Result<>(Code.NO_CONTENT, "port:" + this.port + ":no content", result);
        }
    }

    @Override
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
    public Result<Integer> delete(Long id) {
        Integer result = payDao.delete(id);
        log.info("删除结果:" + result);
        if (result > 0) {
            return new Result<>(Code.SUCCESS, "port:" + this.port + ":success", result);
        } else {
            return new Result<>(Code.NO_RESPONSE, "port:" + this.port + ":delete fail", result);
        }
    }
}
