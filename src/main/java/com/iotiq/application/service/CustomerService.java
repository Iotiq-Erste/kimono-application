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

import java.util.Objects;

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
        customerDto.setSizeInfo(Objects.requireNonNullElseGet(customer.getSizeInfo(), SizeInfo::new));
        customerDto.setMedicalData(Objects.requireNonNullElseGet(customer.getMedicalData(), MedicalData::new));
        customerDto.setCart(customerDto.getCart());
        customerDto.setOrders(ModelMapperUtil.map(customer.getOrders(), OrderDto.class));

        return customerDto;
    }

    private ContactInfo getContactInfo(Customer customer) {
        ContactInfo contactInfo = new ContactInfo();

        contactInfo.setAddress(Objects.requireNonNullElseGet(customer.getAddress(), Address::new));
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setFirstName(customer.getUser().getPersonalInfo().getFirstName());
        basicInfo.setLastName(customer.getUser().getPersonalInfo().getLastName());
        basicInfo.setEmail(customer.getUser().getPersonalInfo().getEmail());
        basicInfo.setPhoneNumber(customer.getUser().getPersonalInfo().getPhoneNumber());
        contactInfo.setBasicInfo(basicInfo);
        return contactInfo;
    }

    private Customer createCustomer(User currentUser) {
        Customer customer = new Customer();
        customer.setUser(currentUser);
        customer.setAddress(new Address());
        customer.setMedicalData(new MedicalData());
        customer.setSizeInfo(new SizeInfo());
        return customerRepository.save(customer);
    }
}
