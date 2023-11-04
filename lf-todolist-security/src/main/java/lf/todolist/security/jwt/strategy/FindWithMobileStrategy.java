package lf.todolist.security.jwt.strategy;

import lf.todolist.security.domain.UserDetails;
import lf.todolist.security.domain.MobilePrincipal;
import lf.todolist.security.provider.MobilePasswordAuthenticationToken;
import lf.todolist.security.service.UserDetailsServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindWithMobileStrategy implements FindStrategy {

    public static final String MOBILE_STRATEGY_NAME = "MOBILE";

    UserDetailsServices userDetailsServices;

    @Override
    public MobilePasswordAuthenticationToken findAuthenticationTokenFrom(String secretKey, String token) {
        UserDetails userDetails = getUserDetailsFrom(secretKey, token);
        return new MobilePasswordAuthenticationToken(MobilePrincipal.of(userDetails.getCountryCode(), userDetails.getMobile()),
                userDetails.getPassword(), userDetails.getUuid(), userDetails.getAuthorities());
    }

    private UserDetails getUserDetailsFrom(String secretKey, String token) {
        MobilePrincipal principal = extractMobilePrincipalFrom(secretKey, token);
        return userDetailsServices.loadUserDetailsByMobile(principal.getCountryCode(), principal.getMobile());
    }

    private MobilePrincipal extractMobilePrincipalFrom(String secretKey, String token) {
        Claims tokenBody = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        String countryCode = tokenBody.get("countryCode").toString();
        String mobile = tokenBody.get("mobile").toString();
        return MobilePrincipal.of(countryCode, mobile);
    }
}
