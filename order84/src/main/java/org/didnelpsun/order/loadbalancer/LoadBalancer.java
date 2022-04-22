// LoadBalancer.java
package org.didnelpsun.order.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    // 获取对应服务实例的方法
    ServiceInstance getInstance(List<ServiceInstance> serviceInstanceList);
}
