package lf.todolist.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lf.todolist.intf.exceptions.BusinessException;
import lf.todolist.security.domain.AuthStrategy;
import lf.todolist.security.jwt.strategy.FindStrategy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenProvider implements InitializingBean {

    /**
     * TODO
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here.
     * Ideally, in a microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key}")
    String secretKey;

    @Value("${security.jwt.token.expire-length}")
    long validityInSeconds;

    final Map<String, FindStrategy> findUserDetailsStrategyMap;

    public JwtTokenProvider(Map<String, FindStrategy> findUserDetailsStrategyMap) {
        this.findUserDetailsStrategyMap = findUserDetailsStrategyMap;
    }

    public String createTokenFrom(String email, Collection<? extends GrantedAuthority> authorities) {

        Claims claims = Jwts.claims().setSubject(AuthStrategy.EMAIL.name());
        claims.put("authorities", authorities);
        claims.put("email", email);

        return createTokenFrom(claims);
    }

    public String createTokenFrom(String countryCode, String mobile, Collection<? extends GrantedAuthority> authorities) {

        Claims claims = Jwts.claims().setSubject(AuthStrategy.MOBILE.name());
        claims.put("authorities", authorities);
        claims.put("countryCode", countryCode);
        claims.put("mobile", mobile);

        return createTokenFrom(claims);
    }

    public Authentication getAuthentication(String token) {
        String subject = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
        FindStrategy strategy = findUserDetailsStrategyMap.get(subject);
        return strategy.findAuthenticationTokenFrom(secretKey, token);
    }

    @Override
    public void afterPropertiesSet() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String resolveToken(String bearerToken) {
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new BusinessException("Expired or invalid JWT token");
        }
    }

    private String createTokenFrom(Claims claims) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime validity = LocalDateTime.ofInstant(
                now.plusSeconds(validityInSeconds).toInstant(ZoneOffset.UTC), ZoneOffset.UTC);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
                .setExpiration(Date.from(validity.toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
