package ua.xet.ConferenceApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ua.xet.ConferenceApp.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionConfig encodedPass;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth
                .userDetailsService(userService)
                .passwordEncoder(encodedPass.getPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/stat/*", "/users").hasRole("ADMIN")
                .antMatchers("/conference", "/schedule", "/conf/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/register/*", "/home", "/").permitAll()
                .antMatchers("/index").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}
