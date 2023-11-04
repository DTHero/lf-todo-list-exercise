package lf.todolist.security.provider;

import lf.todolist.security.domain.UserDetails;
import lf.todolist.security.service.UserDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@AllArgsConstructor
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {

    UserDetailsServices userDetailsServices;
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        String credentials = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsServices.loadUserDetailsByEmail(principal);
        if (Objects.equals(userDetails.getEmail(), principal) && passwordEncoder.matches(credentials, userDetails.getPassword())) {
            return new EmailPasswordAuthenticationToken(principal, credentials, userDetails.getUuid(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid email and password credentials");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(EmailPasswordAuthenticationToken.class);
    }
}
