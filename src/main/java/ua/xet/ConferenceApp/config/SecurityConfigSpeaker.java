package ua.xet.ConferenceApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(3)
public class SecurityConfigSpeaker extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/rating")
                .authorizeRequests()
                .anyRequest().hasAnyRole("ADMIN","SPEAKER")
                .and().httpBasic().authenticationEntryPoint(authenticationEntryPointSpeaker());
    }
    @Bean
    AuthenticationEntryPoint authenticationEntryPointSpeaker(){
        BasicAuthenticationEntryPoint entryPoint =
                new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("speaker realm");
        return entryPoint;
    }
}
