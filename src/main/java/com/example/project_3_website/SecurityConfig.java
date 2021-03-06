package com.example.project_3_website;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login.html", "/index.html","/login", "/accountPage.html", "/createAccount",
                        "/createAccount.html","/fragments.html","/team.html")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }

}
