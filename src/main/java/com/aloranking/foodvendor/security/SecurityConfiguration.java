package com.aloranking.foodvendor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register/customer","/register/vendor").permitAll()
                .antMatchers("/customer/{id}/set-password","/vendor/{id}/set-password").permitAll()
               .antMatchers("/customer/all","/customer/{id}","/home/menus","/vendor/{id}","/vendor/all").permitAll()
                .antMatchers("/home/customer/**").permitAll()
                .antMatchers( "/home/vendor/**").permitAll()
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().formLogin()
                .defaultSuccessUrl("/home");



    }


}
