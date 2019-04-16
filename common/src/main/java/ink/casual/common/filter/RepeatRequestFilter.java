package ink.casual.common.filter;

import ink.casual.common.util.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author lwt
 * @date 2018/12/26 15:19
 * 重复请求拦截器
 */
@Component
public class RepeatRequestFilter implements WebFilter {

    private Logger logger = LoggerFactory.getLogger(RepeatRequestFilter.class);

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (HttpMethod.OPTIONS.equals(request.getMethod())){
            return chain.filter(exchange);
        }
        String key = request.getId()+":"+request.getPath().value();
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add("Access-Control-Allow-Origin","*");
        response.getHeaders().add("Access-Control-Allow-Headers","*");
        if(redisService.hasKey(key)){
            response.setStatusCode(HttpStatus.ALREADY_REPORTED);
            logger.warn("重复请求已拦截:{}",key);
            return Mono.empty();
        }else {
            redisService.set(key,1, Duration.ofSeconds(2));
        }
        return chain.filter(exchange);
    }
}
