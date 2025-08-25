package com.wookis.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // Webflux를 활용한 비동기 방식에서 request와 response 객체를 가져옵니다.
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            //Custom Pre Filter
            log.info("Custom Pre Filter executed: requestId -> {}", request.getId());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //Custom Post Filter
                log.info("Custom Post Filter executed: response code -> {}", response.getStatusCode());
            }));
        });
    }

    public static class Config {

    }
}
