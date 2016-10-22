package com.softserve.edu.delivery.config;

import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import com.softserve.edu.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String CUSTOMER_ROLE = Role.CUSTOMER.getName();
    private static final String DRIVER_ROLE = Role.DRIVER.getName();
    private static final String ADMIN_ROLE = Role.ADMIN.getName();
    private static final String MODERATOR_ROLE = Role.MODERATOR.getName();

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

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ApplicationErrorPageRegistrator();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("customer@delivery.com").password("customer").roles(CUSTOMER_ROLE);
        auth.inMemoryAuthentication().withUser("driver@delivery.com").password("driver").roles(DRIVER_ROLE);
        auth.inMemoryAuthentication().withUser("admin@delivery.com").password("admin").roles(ADMIN_ROLE);
        auth.inMemoryAuthentication().withUser("moderator@delivery.com").password("moderator").roles(MODERATOR_ROLE);

        auth.userDetailsService(this.userDetailsService);
        auth.authenticationProvider(authProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/home", "/welcome").permitAll()
                .antMatchers("/login").anonymous()
                .antMatchers("/registration", "/register", "/driverRegister", "/driverRegistration").anonymous()
                .antMatchers("/admin", "/admin/**").hasRole(ADMIN_ROLE)
                .antMatchers("/moderator", "/moderator/**").hasRole(MODERATOR_ROLE)
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/authRedirect")
                .failureUrl("/login?auth=false")
                .and().logout().logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }
}