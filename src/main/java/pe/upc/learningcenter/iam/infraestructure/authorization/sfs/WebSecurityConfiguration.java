package pe.upc.learningcenter.iam.infraestructure.authorization.sfs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(configurer -> configurer.configurationSource(x -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }));

        http.csrf(csrfConfigurer -> csrfConfigurer.disable());

        http.authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                        .requestMatchers("/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        return http.build();
    }

}
