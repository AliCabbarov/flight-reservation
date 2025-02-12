package ingress.userms.config;

import ingress.common.service.JwtService;
import ingress.common.model.exception.Handler;
import ingress.common.config.JwtSessionData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import({Handler.class, JwtService.class})
public class BeanConfig {
    @Bean
    public JwtSessionData jwtSessionData() {
        return new JwtSessionData();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
