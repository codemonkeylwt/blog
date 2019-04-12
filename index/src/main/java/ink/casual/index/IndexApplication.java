package ink.casual.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author lwt
 * @date 2019/3/27 16:49
 */
@SpringBootApplication(scanBasePackages = {"ink.casual.*"})
@EnableWebFlux
@EnableFeignClients(basePackages = {"ink.casual.*.common.provider"})
@EnableDiscoveryClient
public class IndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndexApplication.class, args);
    }

}
