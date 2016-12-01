package com.softserve.edu.delivery.config;

import com.softserve.edu.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userService")
    private UserService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication();
        auth.userDetailsService(this.userDetailsService);
        auth.authenticationProvider(authProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(STATIC_RESOURCES).permitAll()
                .antMatchers(WELCOME_PAGE, AUTH_REDIRECT_URL,
                        REGISTRATION_EMAIL_VERIFIED_URL, ABOUT_US).permitAll()
                .antMatchers(ERROR_URLs).permitAll()
                .antMatchers(SOCIAL_URLs).permitAll()
                .antMatchers(LOGIN_PAGE, REGISTRATION_PAGE).anonymous()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(LOGIN_PAGE)
                .loginProcessingUrl(LOGIN_PROCESS_URL)
                .usernameParameter(USERNAME_PARAM)
                .passwordParameter(PASSWORD_PARAM)
                .defaultSuccessUrl(AUTH_REDIRECT_URL)
                .failureUrl(FAILURE_LOGIN_URL)
                .and().logout().logoutSuccessUrl(LOGOUT_URL)
                .invalidateHttpSession(true)
                .and().exceptionHandling().accessDeniedPage(ACCESS_DENIED_URL);
    }
}