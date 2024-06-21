package pe.upc.learningcenter.iam.infraestructure.authorization.sfs.pipeline;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.upc.learningcenter.iam.infraestructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import pe.upc.learningcenter.iam.infraestructure.tokens.jwt.BearerTokenService;

import java.io.IOException;

public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);
    private final BearerTokenService tokenService;

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;


    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = tokenService.getBearerTokenFrom(request);
            LOGGER.info("Token: {}", token);

            if (token != null && tokenService.validateToken(token)) {
                String username = tokenService.getUsernameFromToken(token);
                var userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
            } else {
                LOGGER.warn("Invalid token");
            }
        } catch(Exception ex) {
                LOGGER.error("Cannot set user authentication: {}", ex);
        }

        filterChain.doFilter(request, response);
    }
}
