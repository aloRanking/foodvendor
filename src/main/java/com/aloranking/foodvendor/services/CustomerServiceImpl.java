package com.aloranking.foodvendor.services;


import com.aloranking.foodvendor.models.AuthUser;
import com.aloranking.foodvendor.models.Customer;
import com.aloranking.foodvendor.repositories.AuthUserRepository;
import com.aloranking.foodvendor.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl  implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthUserRepository authUserRepository;


    @Override
    public void saveCustomer(Customer customer) {

       // customer.setPassword(passwordEncoder.encode(customer.getPassword()));
       /* Role customerRole = roleRepository.findByRole("CUSTOMER");
        AuthUser authUser = new AuthUser();
        authUser.setEmail(customer.getEmail());
        authUser.setPassword(passwordEncoder.encode(customer.getPassword()));
        //authUser.setRoles((customerRole));
        authUserRepository.saveAndFlush(authUser);
        customer.setAuthUser(authUser);
        customerRepository.saveAndFlush(customer);
*/

    }

    @Override
    public Customer addUser(Customer customer, String password) {
        AuthUser authUser= createUser(customer.getEmail(), password, customer);
        //authUser.setCustomer(customer);
        customer.setAuthUser(authUser);

        return customerRepository.save(customer);
    }

    @Override
    public boolean isEmailAlreadyPresent(Customer customer) {
        return false;
    }

    public AuthUser createUser(String email, String password, Customer customer){
        AuthUser authUser = new AuthUser();
        authUser.setEmail(email);
        authUser.setPassword(password);
        authUser.setCustomer(customer);
    //    Role role = roleRepository.findByRole("CUSTOMER");
   //     authUser.setRoles(role);
        authUserRepository.save(authUser);
        return authUser;
    }


    @Override
    public Customer getCustomer(Long id) {

        return customerRepository.getOne(id);
    }




}
