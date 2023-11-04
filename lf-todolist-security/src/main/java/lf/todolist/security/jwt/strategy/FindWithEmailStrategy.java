package lf.todolist.security.jwt.strategy;

import lf.todolist.security.domain.UserDetails;
import lf.todolist.security.provider.EmailPasswordAuthenticationToken;
import lf.todolist.security.service.UserDetailsServices;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindWithEmailStrategy implements FindStrategy {

    public static final String EMAIL_STRATEGY_NAME = "EMAIL";

    UserDetailsServices userDetailsServices;

    @Override
    public EmailPasswordAuthenticationToken findAuthenticationTokenFrom(String secretKey, String token) {
        UserDetails userDetails = getUserDetails(secretKey, token);
        return new EmailPasswordAuthenticationToken(userDetails.getEmail(), userDetails.getPassword(),
                userDetails.getUuid(), userDetails.getAuthorities());
    }

    private UserDetails getUserDetails(String secretKey, String token) {
        String emailFromToken = extractEmailFrom(secretKey, token);
        return userDetailsServices.loadUserDetailsByEmail(emailFromToken);
    }

    private String extractEmailFrom(String secretKey, String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("email")
                .toString();
    }
}
