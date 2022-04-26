// GatewayConfig.java
package org.didnelpsun.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
//    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        routes.route("pay8001", f -> f.path("/pay/**").uri("http://localhost:8001/pay")).build();
//        routes.route("order81", f -> f.path("/order/**").uri("http://localhost:81/order")).build();
        routes.route("pay", f -> f.path("/pay/**").uri("lb://pay/pay")).build();
        routes.route("order", f -> f.path("/order/**").uri("lb://order/order")).build();
        return routes.build();
    }
}
