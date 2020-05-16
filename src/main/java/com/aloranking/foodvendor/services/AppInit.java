package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.Role;
import com.aloranking.foodvendor.repositories.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AppInit implements InitializingBean {


    @Autowired
private RoleRepository repo;

    @Override
public void afterPropertiesSet() throws Exception {
        Boolean notPresent = repo.count() <= 0;
        if( ! notPresent )
    return;
        if (notPresent)
    createRoles();
}

public void createRoles(){
    List<Role> roles = new ArrayList<>();
    Role role = new Role("VENDOR");
    Role role2 = new Role("CUSTOMER");

    roles.add(role);
    roles.add(role2) ;
    repo.saveAll(roles);
}
}
