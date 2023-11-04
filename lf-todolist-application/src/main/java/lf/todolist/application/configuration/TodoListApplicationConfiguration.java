package lf.todolist.application.configuration;

import lf.todolist.application.service.identity.AuthApplicationService;
import lf.todolist.application.service.identity.UserApplicationService;
import lf.todolist.application.service.identity.strategy.EmailUserStrategy;
import lf.todolist.application.service.identity.strategy.MobileUserStrategy;
import lf.todolist.application.service.identity.strategy.UserStrategy;
import lf.todolist.application.service.todo.TodoApplicationService;
import lf.todolist.intf.adapters.TodoListAdapter;
import lf.todolist.intf.adapters.UserAdapter;
import lf.todolist.security.configuration.TodoListSecurityConfiguration;
import lf.todolist.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Map;

import static lf.todolist.application.service.identity.strategy.EmailUserStrategy.EMAIL_USER_STRATEGY;
import static lf.todolist.application.service.identity.strategy.MobileUserStrategy.MOBILE_USER_STRATEGY;

@Configuration
@Import({TodoListSecurityConfiguration.class})
public class TodoListApplicationConfiguration {

    @Bean
    public AuthApplicationService authApplicationService(JwtTokenProvider jwtTokenProvider,
                                                         AuthenticationManager authenticationManager) {
        return new AuthApplicationService(jwtTokenProvider, authenticationManager);
    }

    @Bean
    public UserApplicationService userApplicationService(Map<String, UserStrategy> userStrategyMap) {
        return new UserApplicationService(userStrategyMap);
    }

    @Bean
    public TodoApplicationService todoApplicationService(UserApplicationService userApplicationService, TodoListAdapter todoListAdapter) {
        return new TodoApplicationService(userApplicationService, todoListAdapter);
    }

    @Bean(EMAIL_USER_STRATEGY)
    public UserStrategy emailUserStrategy(UserAdapter userAdapter) {
        return new EmailUserStrategy(userAdapter);
    }

    @Bean(MOBILE_USER_STRATEGY)
    public UserStrategy mobileUserStrategy(UserAdapter userAdapter) {
        return new MobileUserStrategy(userAdapter);
    }
}
