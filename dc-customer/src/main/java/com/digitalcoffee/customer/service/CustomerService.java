package com.digitalcoffee.customer.service;

import com.digitalcoffee.customer.dto.Customer;
import com.digitalcoffee.customer.dto.CustomerDetails;
import com.digitalcoffee.customer.mapper.CustomerMapper;
import com.digitalcoffee.customer.model.CustomerRoot;
import com.digitalcoffee.customer.proxy.Order;
import com.digitalcoffee.customer.proxy.OrderServiceProxy;
import com.digitalcoffee.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderServiceProxy orderService;

    @Autowired
    private CustomerMapper mapper;

    public CustomerRoot save(CustomerRoot customerRoot){
        return customerRepository.save(customerRoot);
    }

    public CustomerDetails getCustomerByUsername(String username){
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomer(mapper.mapToDto(customerRepository.findByUsername(username).get()));
        customerDetails.setOrders(getOrderByCustomer(username));
        return customerDetails;
    }

    public List<Order> getOrderByCustomer(String username){
        return orderService.getCustomerOrders(username);
    }

    public List<CustomerDetails> getAllCustomers(){
       List<CustomerRoot> customerRoots = customerRepository.findAll();
        return
                customerRoots.stream().map(customerRoot -> {
            CustomerDetails customerDetails =new CustomerDetails();
            customerDetails.setCustomer(mapper.mapToDto(customerRoot));
            customerDetails.setOrders(getOrderByCustomer(customerRoot.getUsername()));
            return customerDetails;
        }).collect(Collectors.toList());
    }

    public void createCustomer(String username) {
        customerRepository.save(CustomerRoot.builder().username(username).build());
    }

    public CustomerDetails updateCustomer(Customer customer) {
        customerRepository.findByUsername(customer.getUsername())
                        .ifPresent(c->{
                            mapper.updateCustomerRoot(c, customer);
                            customerRepository.save(c);
                        });

        return getCustomerByUsername(customer.getUsername());
    }
}
