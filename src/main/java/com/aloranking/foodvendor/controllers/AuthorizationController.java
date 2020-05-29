package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.exceptions.UserNotFoundException;
import com.aloranking.foodvendor.models.*;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.*;
import com.aloranking.foodvendor.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthUserDetailService authUserDetailService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private AuthUserRepository authUserRepository;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?>loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {

         authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password",e);
        }
        final UserDetails userDetails = authUserDetailService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping(value = "/users")
    public List<AuthUser> listAuthUsers(){
        return authUserRepository.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public AuthUser getUser(@PathVariable Long id) {
        return authUserRepository.getOne(id);
    }














}
