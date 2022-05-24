// SentinelService.java
package org.didnelpsun.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class SentinelService {
    @SentinelResource("service")
    public String service(){
        return "service";
    }
}
