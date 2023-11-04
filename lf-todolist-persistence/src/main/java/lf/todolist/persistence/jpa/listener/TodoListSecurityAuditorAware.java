package lf.todolist.persistence.jpa.listener;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class TodoListSecurityAuditorAware implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        return Optional
                .of(authentication)
                // User UUID
                .map(Authentication::getDetails)
                .map(Object::toString);
    }
}
