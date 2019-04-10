package ink.casual.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author lwt
 * @date 2019/4/10 9:06
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableWebFlux
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
