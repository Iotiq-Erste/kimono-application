package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.MedicalData;
import com.iotiq.application.domain.Order;
import com.iotiq.application.domain.SizeInfo;
import com.iotiq.application.messages.customer.CustomerDto;
import com.iotiq.application.messages.customer.CustomerUpdateRequest;
import com.iotiq.application.messages.customer.contact.Address;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.customer.contact.ContactInfo;
import com.iotiq.application.messages.order.OrderDto;
import com.iotiq.application.repository.CustomerRepository;
import com.iotiq.commons.exceptions.EntityNotFoundException;
import com.iotiq.user.domain.Person;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final private CustomerRepository customerRepository;
    final private UserService userService;

    @Transactional
    public void update(CustomerUpdateRequest request) {
        Customer customer = getCurrentCustomer();
        if (request.getContactInfo() != null) {
            if (request.getContactInfo().getBasicInfo() != null) {
                customer.getUser().setPersonalInfo(Objects.requireNonNullElseGet(customer.getUser().getPersonalInfo(), Person::new));
                ModelMapperUtil.map(request.getContactInfo().getBasicInfo(), customer.getUser().getPersonalInfo());
            }
            if (request.getContactInfo().getAddress() != null) {
                customer.setAddress(Objects.requireNonNullElseGet(customer.getAddress(), Address::new));
                ModelMapperUtil.map(request.getContactInfo().getAddress(), customer.getAddress());
            }
        }
        if (request.getMedicalData() != null) {
            customer.setMedicalData(Objects.requireNonNullElseGet(customer.getMedicalData(), MedicalData::new));
            if(customer.getMedicalData().getClothingSelection() != null) customer.getMedicalData().getClothingSelection().clear();
            if(customer.getMedicalData().getBodyRegions() != null) customer.getMedicalData().getBodyRegions().clear();
            if(customer.getMedicalData().getAllergiesSensitivities() != null) customer.getMedicalData().getAllergiesSensitivities().clear();
            if(customer.getMedicalData().getPastHealthIssues() != null) customer.getMedicalData().getPastHealthIssues().clear();
            if(customer.getMedicalData().getTreatmentWithMedications() != null) customer.getMedicalData().getTreatmentWithMedications().clear();
            if(customer.getMedicalData().getMedicalHistory() != null) customer.getMedicalData().getMedicalHistory().clear();
            ModelMapperUtil.map(request.getMedicalData(), customer.getMedicalData());

        }
        if (request.getSizeInfo() != null) {
            customer.setSizeInfo(Objects.requireNonNullElseGet(customer.getSizeInfo(), SizeInfo::new));
            ModelMapperUtil.map(request.getSizeInfo(), customer.getSizeInfo());
        }

        customerRepository.save(customer);
    }

    @Transactional
    public Customer getCurrentCustomer() {
        User currentUser = userService.getCurrentUser();
        return customerRepository.findByUser(currentUser).orElseThrow(() -> new EntityNotFoundException(Customer.ENTITY_NAME));
    }

    @Transactional
    public CustomerDto getCustomer() {
        Customer customer = getCurrentCustomer();

        CustomerDto customerDto = new CustomerDto();

        customerDto.setContactInfo(getContactInfo(customer));
        customerDto.setSizeInfo(Objects.requireNonNullElseGet(customer.getSizeInfo(), SizeInfo::new));
        customerDto.setMedicalData(Objects.requireNonNullElseGet(customer.getMedicalData(), MedicalData::new));
        customerDto.setCart(customerDto.getCart());
        customerDto.setOrders(getLastTwoOrders(customer.getOrders()));

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

    @Transactional
    public void createIfNotExists(User currentUser) {
        Optional<Customer> customer = customerRepository.findByUser(currentUser);

        if(customer.isEmpty()) {
            createCustomer(currentUser);
        }
    }

    @Transactional
    public void createCustomer(User currentUser) {
        Customer customer = new Customer();
        customer.setUser(currentUser);
        customer.setAddress(new Address());
        customer.setMedicalData(new MedicalData());
        customer.setSizeInfo(new SizeInfo());
        customerRepository.save(customer);
    }

    private List<OrderDto> getLastTwoOrders(List<Order> orderList) {
        if (CollectionUtils.isEmpty(orderList)) {
            return Collections.emptyList();
        }
        return orderList.stream().map(order -> ModelMapperUtil.map(order, OrderDto.class)).sorted(Comparator.comparing(OrderDto::getOrderDate).reversed()).limit(2).collect(Collectors.toList());
    }
}
