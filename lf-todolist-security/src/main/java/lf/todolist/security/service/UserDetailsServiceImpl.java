package lf.todolist.security.service;

import lf.todolist.intf.adapters.UserAdapter;
import lf.todolist.intf.dto.User;
import lf.todolist.security.domain.AuthUser;
import lf.todolist.security.domain.UserDetails;
import lf.todolist.security.exception.EmailNotFoundException;
import lf.todolist.security.exception.MobileNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsServices {

    @Value("${security.max-retries}")
    Integer maxRetries = 5;

    UserAdapter userAdapter;

    @Override
    public UserDetails loadUserDetailsByEmail(String email) throws EmailNotFoundException {
        Optional<User> optionalUser = userAdapter.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new EmailNotFoundException("User with email '" + email + "' is not found");
        }
        return buildUserInfo(optionalUser.get());
    }

    @Override
    public UserDetails loadUserDetailsByMobile(String countryCode, String mobile) throws MobileNotFoundException {
        Optional<User> optionalUser = userAdapter.findByUserMobile(countryCode, mobile);
        if (optionalUser.isEmpty()) {
            throw new MobileNotFoundException("User with mobile '" + countryCode + " " + mobile + "' is not found");
        }
        return buildUserInfo(optionalUser.get());
    }

    private boolean hasDisabled(Integer numberOfRetries) {
        return Optional
                .ofNullable(numberOfRetries)
                .map(times -> times >= maxRetries)
                .orElse(false);
    }

    private UserDetails buildUserInfo(User user) {
        return AuthUser
                .withUsername(user.getUsername())
                .email(user.getEmail())
                .countryCode(user.getCountryCode())
                .mobile(user.getMobile())
                .password(user.getPassword())
                .authority(user.getRole())
                .accountExpired(false)
                .accountLocked(user.getLocked())
                .credentialsExpired(false)
                .disabled(hasDisabled(user.getNumberOfRetries()))
                .build();
    }
}
