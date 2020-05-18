package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AuthUserDetails implements UserDetails {


    private AuthUser authUser;

    public AuthUserDetails(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = authUser.getRole();
        System.out.println(role);
        /*Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : authUser.getRole()){
            grantedAuthorities.add(new SimpleGrantedAuthority(roles.getName()));
        }*/
        return Arrays.asList( new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
