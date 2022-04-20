// IPayService.java
package org.didnelpsun.service;

import org.didnelpsun.entity.Pay;
import org.didnelpsun.entity.Result;

import java.util.List;

public interface IPayService {
    Result<Pay> select(Long id);
    Result<List<Pay>> selects();
    Result<Integer> insert(Pay pay);
    Result<Integer> update(Pay pay);
    Result<Integer> delete(Long id);
}
