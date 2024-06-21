package pe.upc.learningcenter.iam.infraestructure.tokens.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pe.upc.learningcenter.iam.infraestructure.tokens.jwt.BearerTokenService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenServiceImpl implements BearerTokenService {
    private final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String AUTHORIZATION_PARAMETER_NAME = "Authorization";
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_BEGIN_INDEX = 7;
    private final String secret = "wlRUFve73uJkP95NVimqamewoWR3zH0s";

    private final int expirationDays = 1;

    @Override
    public String getBearerTokenFrom(HttpServletRequest request) {
        String authorizationParam = request.getHeader(AUTHORIZATION_PARAMETER_NAME);
        if (authorizationParam != null && authorizationParam.startsWith(BEARER_TOKEN_PREFIX)) {
            return authorizationParam.substring(TOKEN_BEGIN_INDEX);
        }
        return null;
    }

    public SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String buildBearerToken(String username) {
        var issueAt = new Date();
        var expiration = DateUtils.addDays(issueAt, expirationDays);
        var key = getSigningKey();
        return Jwts.builder()
                .subject(username)
                .issuedAt(issueAt)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    @Override
    public String generateBearerToken(Authentication authentication) {
        return buildBearerToken(authentication.getName());
    }

    @Override
    public String generateToken(String username) {
        return buildBearerToken(username);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
        return claimsResolvers.apply(claims);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JSON WEB Token Signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JSON WEB Token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JSON WEB Token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JSON WEB Token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JSON WEB Token claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
