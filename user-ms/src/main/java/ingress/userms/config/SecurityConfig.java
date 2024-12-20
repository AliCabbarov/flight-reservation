package ingress.userms.config;

import ingress.common.model.constant.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationService jwtAuthenticationService;


    @Bean
    @SuppressWarnings("all")
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .csrf(AbstractHttpConfigurer::disable)
                .cors();
        security
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                EndPoints.SWAGGER_V2,
                                EndPoints.SWAGGER_V3,
                                EndPoints.SWAGGER_V3_ALL,
                                EndPoints.SWAGGER_RESOURCE,
                                EndPoints.SWAGGER_RESOURCE_ALL,
                                EndPoints.SWAGGER_CONFIGURATION_UI,
                                EndPoints.SWAGGER_CONFIGURATION_SECURITY,
                                EndPoints.SWAGGER_UI,
                                EndPoints.SWAGGER_WEBJARS,
                                EndPoints.SWAGGER_UI_HTML).permitAll()
                        .requestMatchers("/api/v1/operators/register").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/operators/register").permitAll()
                        .requestMatchers("/api/v1/operators/admin/operator/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/users/info/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationService, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }
}