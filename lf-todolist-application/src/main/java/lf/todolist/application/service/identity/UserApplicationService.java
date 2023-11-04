package lf.todolist.application.service.identity;

import lf.todolist.application.service.identity.strategy.UserStrategy;
import lf.todolist.intf.dto.User;
import lf.todolist.intf.enums.RoleEnum;
import lf.todolist.intf.exceptions.TechnicalException;
import lf.todolist.security.domain.AuthStrategy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserApplicationService {

    static Map<AuthStrategy, String> AUTH_STRATEGY_USER_MAP = new EnumMap<>(AuthStrategy.class);

    static {
        AUTH_STRATEGY_USER_MAP.put(AuthStrategy.EMAIL, "EMAIL_USER_STRATEGY");
        AUTH_STRATEGY_USER_MAP.put(AuthStrategy.MOBILE, "MOBILE_USER_STRATEGY");
    }

    Map<String, UserStrategy> userStrategyMap;

    public User getProfileFor(Authentication authentication) {
        String strategyName = authentication.getName();
        Object principal = authentication.getPrincipal();
        UserStrategy userStrategy = userStrategyMap.get(AUTH_STRATEGY_USER_MAP.get(AuthStrategy.valueOf(strategyName)));
        Optional<User> optionalUser = userStrategy.findFor(principal);
        return optionalUser.orElseThrow(() -> new TechnicalException("Technical error occurred, cannot find user"));
    }

    public boolean isAdminUser(Authentication authentication) {
        User user = getProfileFor(authentication);
        return user.getRole() == RoleEnum.ADMIN;
    }
}
