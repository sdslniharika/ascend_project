package com.stackroute.apigateway.filter;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilterFactory<AuthenticationFilter.Config> {

    private static final String API_DOCS_URI = "/v3/api-docs";
    private static final String SWAGGER = "swagger";
    private static final String LOGIN_URI = "login";
    private static final String REGISTER_URI = "addUser";
    private static final String CREATE_USER_URI = "createUserProfile";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String MESSAGE_MISSING_TOKEN = "Authorization token is missing in request";
    private static final String MESSAGE_INVALID_TOKEN = "Authorization token is invalid";
    private static final String MESSAGE_EXPIRED_TOKEN = "Authorization token has expired";


    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String uri = request.getURI().getPath();
            if (uri.endsWith(LOGIN_URI) || uri.endsWith(REGISTER_URI) || uri.endsWith(CREATE_USER_URI) || uri.contains(API_DOCS_URI) || uri.contains(SWAGGER)) {
                return chain.filter(exchange);
            }
            if (this.isAuthMissing(request))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, MESSAGE_MISSING_TOKEN);

            final String token = this.getAuthHeader(request);

            try {
                jwtUtils.isInvalid(token);
            } catch (ExpiredJwtException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, MESSAGE_EXPIRED_TOKEN, e);
            } catch (JwtException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, MESSAGE_INVALID_TOKEN, e);
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        return new Config(AuthenticationFilter.class.getName());
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HEADER_AUTHORIZATION).get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HEADER_AUTHORIZATION);
    }

    public static class Config {

        private String name;

        public Config(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
