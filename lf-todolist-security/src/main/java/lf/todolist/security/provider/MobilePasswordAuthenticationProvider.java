package lf.todolist.security.provider;

import lf.todolist.security.domain.UserDetails;
import lf.todolist.security.domain.MobilePrincipal;
import lf.todolist.security.service.UserDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@AllArgsConstructor
public class MobilePasswordAuthenticationProvider implements AuthenticationProvider {

    UserDetailsServices userDetailsServices;
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobilePrincipal principal = (MobilePrincipal) authentication.getPrincipal();
        String credentials = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsServices.loadUserDetailsByMobile(principal.getCountryCode(), principal.getMobile());
        if (Objects.equals(userDetails.getCountryCode(), principal.getCountryCode())
                && Objects.equals(userDetails.getMobile(), principal.getMobile())
                && passwordEncoder.matches(credentials, userDetails.getPassword())) {
            return new MobilePasswordAuthenticationToken(principal, credentials, userDetails.getUuid(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid mobile and password credentials");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(MobilePasswordAuthenticationToken.class);
    }
}
