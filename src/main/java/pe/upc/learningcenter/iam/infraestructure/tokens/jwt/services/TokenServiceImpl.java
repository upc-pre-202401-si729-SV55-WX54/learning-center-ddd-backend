package pe.upc.learningcenter.iam.infraestructure.tokens.jwt.services;

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
import javax.crypto.spec.SecretKeySpec;
import javax.swing.plaf.PanelUI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenServiceImpl implements BearerTokenService {
    private final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String AUTHORIZATION_PARAMETER_NAME = "Authorization";
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_BEGIN_INDEX = 7;
    private static final int EXPIRATION_MINUTES = 60;


    private String secret = "78e1c8c4378a662db791c5f5f9a673c4b0ae181a8b0a1165027dee8e4263ecc1";

    @Override
    public String getBearerTokenFrom(HttpServletRequest request) {
        var authorizationParameter = request.getHeader(AUTHORIZATION_PARAMETER_NAME);

        if (authorizationParameter != null && authorizationParameter.startsWith(BEARER_TOKEN_PREFIX)) {
            return authorizationParameter.substring(TOKEN_BEGIN_INDEX);
        }
        return null;
    }

    private String buildTokenWithDefaultParameters(String username){
        var issueAt = new Date();
        var expiration = DateUtils.addMinutes(issueAt, EXPIRATION_MINUTES);
        var key = getSecretKey();

        return Jwts.builder()
                .subject(username)
                .issuedAt(issueAt)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    private SecretKey getSecretKey() {
        var keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(Authentication authentication) {
        return buildTokenWithDefaultParameters(authentication.getName());
    }

    @Override
    public String generateToken(String username) {
        return buildTokenWithDefaultParameters(username);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> extractor) {
        Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
        return extractor.apply(claims);
    }

    @Override
    public boolean validateToken(String token) {
        try{
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
            LOGGER.info("Token is valid");
            return true;
        }catch (SignatureException e){
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        }catch (MalformedJwtException e){
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        }catch (ExpiredJwtException e){
            LOGGER.error("Expired JWT token: {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            LOGGER.error("Unsupported JWT token: {}", e.getMessage());
        }catch (IllegalArgumentException e){
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
