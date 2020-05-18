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

   /* @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;*/

    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register/customer").permitAll()
                .antMatchers("/register/vendor").permitAll()
               .antMatchers("/customer/{id}/set-password").permitAll()
                .antMatchers("/vendor/{id}/set-password").permitAll()
                .antMatchers("/customer/get-all").permitAll()
                .antMatchers("/login").hasAnyAuthority("CUSTOMER", "VENDOR")
                .antMatchers("/home/**").hasAnyAuthority("CUSTOMER", "VENDOR")
                .anyRequest().authenticated()
                .and()
                .csrf().disable().formLogin()
                .defaultSuccessUrl("/home");



    }


}
