package com.aloranking.foodvendor.services;

import com.aloranking.foodvendor.models.OrderStatus;
import com.aloranking.foodvendor.models.Role;
import com.aloranking.foodvendor.repositories.OrderStatusRepository;
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
    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
public void afterPropertiesSet() throws Exception {
        boolean rolesNotPresent = repo.count() <= 0;
        boolean orderMssgNotPresent = orderStatusRepository.count()<=0;

        if( ! rolesNotPresent &&  ! orderMssgNotPresent ) {
            return;
        }else if (rolesNotPresent && ! orderMssgNotPresent ) {
            createRoles();
        }else if ( ! rolesNotPresent && orderMssgNotPresent)
            createOrderMssgs();
        else if (rolesNotPresent && orderMssgNotPresent ){
            createRoles();;
            createOrderMssgs();

        }

}

    private void createOrderMssgs() {
        List<OrderStatus> messages = new ArrayList<>();
        OrderStatus mssg1 = new OrderStatus("Pending");
        OrderStatus mssg2 = new OrderStatus("Accepted");
        OrderStatus mssg3 = new OrderStatus("Ready");
        OrderStatus mssg4 = new OrderStatus("Cancelled");

        messages.add(mssg1);
        messages.add(mssg2);
        messages.add(mssg3);
        messages.add(mssg4);
        orderStatusRepository.saveAll(messages);
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
