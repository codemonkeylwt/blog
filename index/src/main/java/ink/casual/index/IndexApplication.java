package ink.casual.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author lwt
 * @date 2019/3/27 16:49
 */
@SpringBootApplication
@EnableWebFlux
@EnableFeignClients
@ComponentScan(basePackages = {"ink.casual.*.common.*"})
public class IndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndexApplication.class, args);
    }
}
