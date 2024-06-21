package pe.upc.learningcenter.iam.infraestructure.authorization.sfs.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import pe.upc.learningcenter.iam.infraestructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import pe.upc.learningcenter.iam.infraestructure.tokens.jwt.BearerTokenService;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final BearerTokenService tokenService;

    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPoint unauthorizedRequestHandlerEntryPoint;

    public WebSecurityConfiguration(BearerTokenService tokenService, @Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService, AuthenticationEntryPoint unauthorizedRequestHandlerEntryPoint) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
        this.unauthorizedRequestHandlerEntryPoint = unauthorizedRequestHandlerEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .cors(configurer -> configurer.configurationSource(x -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of("*"));
                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
                    cors.setAllowedHeaders(List.of("*"));
                    return cors;
                }))
            .csrf(csrfConfigurer -> csrfConfigurer.disable())
            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedRequestHandlerEntryPoint))
            .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers(
                            "/api/v1/authentication/**",
                            "/v3/api-docs/**",
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/swagger-resources/**",
                            "/webjars/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
            .addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

}
