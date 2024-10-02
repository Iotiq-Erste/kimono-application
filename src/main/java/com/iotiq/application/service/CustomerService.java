package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.MedicalData;
import com.iotiq.application.domain.SizeInfo;
import com.iotiq.application.messages.customer.CustomerDto;
import com.iotiq.application.messages.customer.CustomerUpdateRequest;
import com.iotiq.application.messages.customer.contact.Address;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.customer.contact.ContactInfo;
import com.iotiq.application.messages.order.OrderDto;
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
    public void update(CustomerUpdateRequest request) {
        Customer customer = getCurrentCustomer();
        customer.getUser().setPersonalInfo(ModelMapperUtil.map(request.getContactInfo().getBasicInfo(), Person.class));
        customer.setAddress(request.getContactInfo().getAddress());
        customer.setMedicalData(request.getMedicalData());
        customer.setSizeInfo(request.getSizeInfo());

        customer.getUser().setPassword(request.getPassword());

        customerRepository.save(customer);
    }

    @Transactional
    public Customer getCurrentCustomer() {
        User currentUser = userService.getCurrentUser();
        return customerRepository.findByUser(currentUser).orElseGet(() -> createCustomer(currentUser));
    }

    @Transactional
    public CustomerDto getCustomer() {
        Customer customer = getCurrentCustomer();

        CustomerDto customerDto = new CustomerDto();

        customerDto.setContactInfo(getContactInfo(customer));
        customerDto.setSizeInfo(customer.getSizeInfo());
        customerDto.setMedicalData(customer.getMedicalData());
        customerDto.setCart(customerDto.getCart());
        customerDto.setOrders(ModelMapperUtil.map(customer.getOrders(), OrderDto.class));
        customerDto.setActive(customer.isActive());

        return setNullField(customerDto);
    }

    private ContactInfo getContactInfo(Customer customer) {
        ContactInfo contactInfo = new ContactInfo();

        contactInfo.setAddress(customer.getAddress());
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setFirstname(customer.getUser().getPersonalInfo().getFirstName());
        basicInfo.setLastname(customer.getUser().getPersonalInfo().getLastName());
        basicInfo.setEmail(customer.getUser().getPersonalInfo().getEmail());
        basicInfo.setPhoneNumber(customer.getUser().getPersonalInfo().getPhoneNumber());
        contactInfo.setBasicInfo(basicInfo);
        return contactInfo;
    }

    private Customer createCustomer(User currentUser) {
        Customer customer = new Customer();
        customer.setUser(currentUser);
        customer.setActive(true);
        customer.setAddress(new Address());
        customer.setMedicalData(new MedicalData());
        customer.setSizeInfo(new SizeInfo());
        return customerRepository.save(customer);
    }

    private CustomerDto setNullField(CustomerDto customerDto){
        if(customerDto.getContactInfo() == null){
            customerDto.setContactInfo(new ContactInfo());
        }
        if (customerDto.getContactInfo().getBasicInfo() == null){
            customerDto.getContactInfo().setBasicInfo(new BasicInfo());
        }
        if(customerDto.getContactInfo().getAddress() == null){
            customerDto.getContactInfo().setAddress(new Address());
        }
        if(customerDto.getSizeInfo() == null){
            customerDto.setSizeInfo(new SizeInfo());
        }
        if (customerDto.getMedicalData() == null){
            customerDto.setMedicalData(new MedicalData());
        }
        return customerDto;
    }
}
