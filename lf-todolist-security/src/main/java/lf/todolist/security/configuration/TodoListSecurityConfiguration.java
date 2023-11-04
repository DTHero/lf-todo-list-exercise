package lf.todolist.security.configuration;

import lf.todolist.intf.adapters.UserAdapter;
import lf.todolist.security.jwt.JwtTokenProvider;
import lf.todolist.security.jwt.strategy.FindStrategy;
import lf.todolist.security.jwt.strategy.FindWithEmailStrategy;
import lf.todolist.security.jwt.strategy.FindWithMobileStrategy;
import lf.todolist.security.provider.EmailPasswordAuthenticationProvider;
import lf.todolist.security.provider.MobilePasswordAuthenticationProvider;
import lf.todolist.security.service.UserDetailsServiceImpl;
import lf.todolist.security.service.UserDetailsServices;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static lf.todolist.security.jwt.strategy.FindWithEmailStrategy.EMAIL_STRATEGY_NAME;
import static lf.todolist.security.jwt.strategy.FindWithMobileStrategy.MOBILE_STRATEGY_NAME;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoListSecurityConfiguration {

    static int BCRYPT_STRENGTH = 12;

    @Bean
    public UserDetailsServices userDetailsServices(UserAdapter userAdapter) {
        return new UserDetailsServiceImpl(userAdapter);
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(Map<String, FindStrategy> findUserDetailsStrategyMap) {
        return new JwtTokenProvider(findUserDetailsStrategyMap);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
    }

    @Bean
    public MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider(UserDetailsServices userDetailsServices,
                                                                                     PasswordEncoder passwordEncoder) {
        return new MobilePasswordAuthenticationProvider(userDetailsServices, passwordEncoder);
    }

    @Bean
    public EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider(UserDetailsServices userDetailsServices,
                                                                                   PasswordEncoder passwordEncoder) {
        return new EmailPasswordAuthenticationProvider(userDetailsServices, passwordEncoder);
    }

    @Bean(EMAIL_STRATEGY_NAME)
    public FindStrategy findWithEmailStrategy(UserDetailsServices userDetailsServices) {
        return new FindWithEmailStrategy(userDetailsServices);
    }

    @Bean(MOBILE_STRATEGY_NAME)
    public FindStrategy findWithMobileStrategy(UserDetailsServices userDetailsServices) {
        return new FindWithMobileStrategy(userDetailsServices);
    }
}
