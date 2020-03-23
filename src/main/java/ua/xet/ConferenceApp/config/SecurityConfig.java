package ua.xet.ConferenceApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
}
