package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.AuthUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthUserService {


    AuthUser getAuthUser(Long id);
}
