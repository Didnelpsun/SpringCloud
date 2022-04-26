// PermissionFilter.java
package org.didnelpsun.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PermissionFilter implements GlobalFilter, Ordered {
    // 第一个参数为Web服务器交互数据，包括请求和响应，第二个参数为过滤器链
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取参数
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        // 如果不携带这个参数就报错
        if(username == null){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        // 如果过滤成功就继续传递交互数据，否则返回null
        return chain.filter(exchange);
    }

    // 设置加载过滤器的优先级顺序，越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
