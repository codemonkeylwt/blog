package ink.casual.common.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author lwt
 * @date 2019/1/28 22:55
 */
@Configuration
public class CommonWebFluxConfig implements WebFluxConfigurer {

    @Autowired
    private ObjectProvider<CodecCustomizer> codecCustomizers;

    /**
     * 配合配置文件中spring.jackson,更改json返回格式
     *
     */
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        this.codecCustomizers.orderedStream().forEach((customizer) -> {
            customizer.customize(configurer);
        });
    }

}
