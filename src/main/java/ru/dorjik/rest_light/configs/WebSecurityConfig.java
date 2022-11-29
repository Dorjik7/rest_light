package ru.dorjik.rest_light.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;



    private final UserDetailsService userService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler,UserDetailsService userService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                .antMatchers(  "/login", "/error").permitAll()
                .antMatchers("/").hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .exceptionHandling().accessDeniedPage("/user");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }
    }



