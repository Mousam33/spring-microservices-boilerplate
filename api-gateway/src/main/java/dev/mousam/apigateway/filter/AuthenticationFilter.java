package dev.mousam.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationFilter extends
        AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->
                ReactiveSecurityContextHolder.getContext()
                        .filter(Objects::nonNull)
                        .map(securityContext -> securityContext.getAuthentication())
                        .filter(authentication -> authentication instanceof OAuth2AuthenticationToken)
                        .map(authentication -> (OAuth2AuthenticationToken) authentication)
                        .map(oAuth2Authentication -> oAuth2Authentication.getPrincipal())
                        .map(bearerToken -> {
                            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
                            builder
                                    .header("EMAIL", (String) bearerToken.getAttribute("email"))
                                    .header("USERNAME", (String) bearerToken.getAttribute("name"));
                            ServerHttpRequest request = builder.build();
                            return exchange.mutate().request(request).build();
                            })
                        .defaultIfEmpty(exchange)
                        .flatMap(chain::filter);
    }

    public static class Config {
        // ...
    }
}