// CustomizeRole.java
package org.didnelpsun.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomizeRole {
    @Bean
    public IRule customRole(){
        // 定义随机规则
        return new RandomRule();
    }
}