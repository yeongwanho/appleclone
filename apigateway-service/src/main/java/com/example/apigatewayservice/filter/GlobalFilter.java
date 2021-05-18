package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        //Cutom pre Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("Global filter BaseMessage : {}",config.getBaseMessage());

            if (config.isPreLogger()){
                log.info("Global Filter Start : request id -> {}" , request.getId());
            }
            //Custom Post FIlter
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                if (config.isPostLogger()){
               log.info("CUstom POST filter :response code ->{}",response.getStatusCode());
                }
            }));

        };
    }

    public GlobalFilter(){
        super(Config.class);
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }
}
