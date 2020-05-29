package com.aloranking.foodvendor.controllers;

import com.aloranking.foodvendor.models.*;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import com.aloranking.foodvendor.repositories.VendorRepository;
import com.aloranking.foodvendor.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NotificationController  {

@Autowired
private NotificationService notificationService;
@Autowired
private AuthUserRepository authUserRepository;
@Autowired
private AuthUserService authUserService;

    @GetMapping("/notification/{authUserId}/all")
    public List<Notification> getAllAuthUserNotification(@PathVariable Long authUserId){

        AuthUser authUser = authUserService.getAuthUser(authUserId);

        return notificationService.getAllNotifications(authUser);

    }





}
