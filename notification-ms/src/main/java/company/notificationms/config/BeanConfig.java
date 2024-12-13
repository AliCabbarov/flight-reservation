package company.notificationms.config;

import ingress.common.config.JwtSessionData;
import ingress.common.model.exception.Handler;
import ingress.common.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({Handler.class, JwtService.class})
public class BeanConfig {
    @Bean
    public JwtSessionData jwtSessionData() {
        return new JwtSessionData();
    }

}
