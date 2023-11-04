package lf.todolist.application.service.identity.strategy;

import lf.todolist.intf.dto.User;
import lf.todolist.intf.adapters.UserAdapter;
import lf.todolist.security.domain.MobilePrincipal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MobileUserStrategy implements UserStrategy {

    public static final String MOBILE_USER_STRATEGY = "MOBILE_USER_STRATEGY";

    UserAdapter userAdapter;

    @Override
    public Optional<User> findFor(Object principal) {
        MobilePrincipal mobilePrincipal = (MobilePrincipal) principal;
        return userAdapter.findByUserMobile(mobilePrincipal.getCountryCode(), mobilePrincipal.getMobile());
    }
}
