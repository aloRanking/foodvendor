package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email)      throws UsernameNotFoundException {

            AuthUser authUser = authUserRepository.findByEmail(email);

            if (authUser == null) {
                throw new UsernameNotFoundException("Could not find user");
            }

            return new AuthUserDetails(authUser);
        }
    }

