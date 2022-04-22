package org.didnelpsun.order.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl implements LoadBalancer {
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    // 自旋锁获取next值，即第几次访问的次数
    public final int getAndIncrement() {
        int current;
        int next;
        // 如果一直取不到当前值就一直取
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        return next;
    }

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstanceList) {
        // 获取服务实例的下标
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
