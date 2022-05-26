// IPayDao.java
package org.didnelpsun.dao;

import org.didnelpsun.entity.Pay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPayDao {
    Pay select(Long id);
    List<Pay> selects();
    int insert(Pay pay);
    int update(Pay pay);
    int delete(Long id);
}
