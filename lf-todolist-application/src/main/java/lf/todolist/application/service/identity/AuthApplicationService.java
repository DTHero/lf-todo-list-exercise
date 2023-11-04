package lf.todolist.application.service.identity;

import lf.todolist.intf.dto.LoginOut;
import lf.todolist.security.domain.MobilePrincipal;
import lf.todolist.security.jwt.JwtTokenProvider;
import lf.todolist.security.provider.EmailPasswordAuthenticationToken;
import lf.todolist.security.provider.MobilePasswordAuthenticationToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthApplicationService {

    JwtTokenProvider jwtTokenProvider;
    AuthenticationManager authenticationManager;

    public LoginOut loginWithEmail(String email, String password) {
        try {
            Authentication auth =
                    authenticationManager.authenticate(new EmailPasswordAuthenticationToken(email, password));
            return LoginOut.ofLoginSuccess(jwtTokenProvider.createTokenFrom(email, auth.getAuthorities()));
        } catch (AuthenticationException exception) {
            return LoginOut.ofAuthenticationFailed();
        }
    }

    public LoginOut loginWithPhoneNumber(String countryCode, String mobile, String password) {
        try {
            Authentication auth =
                    authenticationManager.authenticate(new MobilePasswordAuthenticationToken(MobilePrincipal.of(countryCode, mobile), password));
            return LoginOut.ofLoginSuccess(jwtTokenProvider.createTokenFrom(countryCode, mobile, auth.getAuthorities()));
        } catch (AuthenticationException exception) {
            return LoginOut.ofAuthenticationFailed();
        }
    }
}
