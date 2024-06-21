package pe.upc.learningcenter.iam.infraestructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import pe.upc.learningcenter.iam.application.internal.outboundservices.tokens.TokenService;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest request);

    String generateBearerToken(Authentication authentication);
}
