package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.messages.customer.CustomerCreateResponse;
import com.iotiq.application.messages.customer.CustomerUpdateRequest;
import com.iotiq.application.repository.CustomerRepository;
import com.iotiq.user.domain.Person;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final private CustomerRepository customerRepository;
    final private UserService userService;

    @Transactional
    public CustomerCreateResponse createCustomer() {
        Customer customer = createCustomer(userService.getCurrentUser());

        return new CustomerCreateResponse(customer.getId());
    }

    @Transactional
    public void inactiveCustomer() {
        Customer customer = getCurrentCustomer();
        customer.setActive(false);
        customerRepository.save(customer);
    }

    @Transactional
    public void update(CustomerUpdateRequest request){
        Customer customer = getCurrentCustomer();
        Person person = ModelMapperUtil.map(request, Person.class);
        customer.getUser().setPersonalInfo(person);
        customer.getUser().setPassword(request.getPassword());

        customerRepository.save(customer);
    }

    @Transactional
    public Customer getCurrentCustomer(){
        User currentUser = userService.getCurrentUser();
        return customerRepository.findByUser(currentUser).orElseGet(() -> createCustomer(currentUser));
    }

    private Customer createCustomer(User currentUser) {
        Customer customer = new Customer();
        customer.setUser(currentUser);
        customer.setActive(true);
        return customerRepository.save(customer);
    }
}
