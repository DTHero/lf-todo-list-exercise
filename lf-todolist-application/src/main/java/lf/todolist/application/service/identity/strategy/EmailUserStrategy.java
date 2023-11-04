package lf.todolist.application.service.identity.strategy;

import lf.todolist.intf.dto.User;
import lf.todolist.intf.adapters.UserAdapter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailUserStrategy implements UserStrategy {

    public static final String EMAIL_USER_STRATEGY = "EMAIL_USER_STRATEGY";

    UserAdapter userAdapter;

    @Override
    public Optional<User> findFor(Object principal) {
        String email = (String) principal;
        return userAdapter.findByEmail(email);
    }
}
