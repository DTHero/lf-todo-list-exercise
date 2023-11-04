package lf.todolist.security.jwt.strategy;

import lf.todolist.security.domain.UserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface FindStrategy {
    AbstractAuthenticationToken findAuthenticationTokenFrom(String secretKey, String token);
}
