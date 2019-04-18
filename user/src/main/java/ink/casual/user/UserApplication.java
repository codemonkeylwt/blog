package ink.casual.user;

import ink.casual.common.util.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author lwt
 * @date 2019/4/10 9:06
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"ink.casual.*"})
@EnableWebFlux
@MapperScan(basePackages = {"ink.casual.user.mapper","ink.casual.common.mapper"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker getSnowflakeIdWorker(){
        return new SnowflakeIdWorker(5,0);
    }

}
