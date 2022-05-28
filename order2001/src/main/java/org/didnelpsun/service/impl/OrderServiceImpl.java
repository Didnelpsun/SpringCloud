// OrderServiceImpl.java
package org.didnelpsun.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.didnelpsun.dao.IOderDao;
import org.didnelpsun.entity.Order;
import org.didnelpsun.entity.Result;
import org.didnelpsun.service.IAccountService;
import org.didnelpsun.service.IOrderService;
import org.didnelpsun.service.IStorageService;
import org.didnelpsun.util.Code;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Resource
    private IOderDao dao;
    @Resource
    private IStorageService storageService;
    @Resource
    private IAccountService accountService;

    @Override
    public Result<Order> select(Long id) {
        Order result = dao.select(id);
        log.info("查询结果:" + result);
        if (result != null) {
            return new Result<>(Code.SUCCESS, "success", result);
        } else {
            return new Result<>(Code.NO_CONTENT, "no content", null);
        }
    }

    @Override
    public Result<List<Order>> selects() {
        List<Order> result = dao.selects();
        log.info("查询结果:" + result);
        if (result != null) {
            return new Result<>(Code.SUCCESS, "success", result);
        } else {
            return new Result<>(Code.NO_CONTENT, "no content", null);
        }
    }

    // 由于这里操作涉及三个数据库，所以必然会使用分布式事务
    @Override
    public Result<Integer> insert(Order order) {
        int orderResult = dao.insert(order);
        int storage = 0;
        int account = 0;
        int status = 0;
        if (orderResult > 0) {
            // 如果新建订单成功就修改库存
            storage = storageService.decrease(order.getProductId(), order.getCount()).getData();
            if (storage > 0) {
                // 如果修改库存成功就修改账户
                account = accountService.decrease(order.getUserId(), order.getMoney()).getData();
                if (account > 0) {
                    // 修改订单状态，从0变成1
                    status = updateStatus(order.getId()).getData();
                    log.info("添加结果:" + orderResult + "，修改库存结果:" + storage + "，修改账户结果:" + account + "，修改状态结果：" + status);
                    return new Result<>(Code.SUCCESS, "success", orderResult);
                }
            }
        }
        log.info("添加结果:" + orderResult + "，修改库存结果:" + storage + "，修改账户结果:" + account + "，修改状态结果：" + status);
        return new Result<>(Code.NO_RESPONSE, "insert fail", orderResult);
    }

    @Override
    public Result<Integer> update(Order order) {
        int result = dao.update(order);
        log.info("更新结果:" + result);
        if (result > 0) {
            return new Result<>(Code.SUCCESS, "success", result);
        } else {
            return new Result<>(Code.NO_RESPONSE, "update fail", result);
        }
    }

    @Override
    public Result<Integer> updateStatus(Long id) {
        int result = dao.updateStatus(id);
        log.info("更新结果:" + result);
        if (result > 0) {
            return new Result<>(Code.SUCCESS, "success", result);
        } else {
            return new Result<>(Code.NO_RESPONSE, "updateStatus fail", result);
        }
    }

    @Override
    public Result<Integer> delete(Long id) {
        Integer result = dao.delete(id);
        log.info("删除结果:" + result);
        if (result > 0) {
            return new Result<>(Code.SUCCESS, "success", result);
        } else {
            return new Result<>(Code.NO_RESPONSE, "delete fail", result);
        }
    }
}
