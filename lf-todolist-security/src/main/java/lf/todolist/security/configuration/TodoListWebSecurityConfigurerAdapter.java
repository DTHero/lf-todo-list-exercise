package lf.todolist.security.configuration;

import lf.todolist.security.jwt.JwtTokenFilterConfigurer;
import lf.todolist.security.jwt.JwtTokenProvider;
import lf.todolist.security.provider.EmailPasswordAuthenticationProvider;
import lf.todolist.security.provider.MobilePasswordAuthenticationProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoListWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    JwtTokenProvider jwtTokenProvider;
    MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider;
    EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // Allow frame for same origin request (e.g. H2 console)
        http.headers().frameOptions().sameOrigin();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.requiresChannel().anyRequest().requiresSecure()
                .and().cors()
                .and().authorizeRequests()
                .mvcMatchers("/**/v1/auth", "/**/ping").permitAll()
                .mvcMatchers("/**/v1/user/profile").authenticated()
                .and();

        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(mobilePasswordAuthenticationProvider)
                .authenticationProvider(emailPasswordAuthenticationProvider);
    }
}
