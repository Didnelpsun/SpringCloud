// IOderDao.java
package org.didnelpsun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.didnelpsun.entity.Order;

import java.util.List;

@Mapper
public interface IOderDao {
    // 查询订单
    Order select(Long id);
    // 查询所有
    List<Order> selects();
    // 新建订单
    Integer insert(Order order);
    // 更新订单
    Integer update(Order order);
    // 更新订单状态
    Integer updateStatus(Long id);
    // 删除订单
    Integer delete(Long id);
}
