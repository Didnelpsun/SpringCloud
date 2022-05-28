// IOrderService.java
package org.didnelpsun.service;

import org.didnelpsun.entity.Order;
import org.didnelpsun.entity.Result;

import java.util.List;

public interface IOrderService {
    Result<Order> select(Long id);

    Result<List<Order>> selects();

    Result<Integer> insert(Order order);

    Result<Integer> update(Order order);

    Result<Integer> updateStatus(Long id);

    Result<Integer> delete(Long id);
}
