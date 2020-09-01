package ua.xet.ConferenceApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(2)
public class SecurityConfigUser extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/conference")
                .antMatcher("/schedule")
                .authorizeRequests()
                .anyRequest().hasAnyRole("ADMIN","USER","SPEAKER")
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPointUser());
    }
    @Bean
    AuthenticationEntryPoint authenticationEntryPointUser(){
        BasicAuthenticationEntryPoint entryPoint =
                new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("user realm");
        return entryPoint;
    }
}
