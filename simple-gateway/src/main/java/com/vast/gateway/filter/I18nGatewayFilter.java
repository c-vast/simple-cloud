package com.vast.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class I18nGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String lang = exchange.getRequest().getQueryParams().getFirst("lang");
        if (lang == null) {
            lang = exchange.getRequest().getHeaders().getFirst("Accept-Language");
        }
        if (StringUtils.hasText(lang)) {
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("X-Lang", lang)
                    .build();
            exchange = exchange.mutate().request(request).build();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
