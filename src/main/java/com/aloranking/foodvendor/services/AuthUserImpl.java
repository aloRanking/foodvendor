package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthUserImpl implements AuthUserService{
    @Autowired
    private AuthUserRepository authUserRepository;
    @Override
    public AuthUser getAuthUser(Long id) {

        return authUserRepository.getOne(id);
    }
}
