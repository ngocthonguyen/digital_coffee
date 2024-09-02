package com.digitalcoffee.customer.controller;

import com.digitalcoffee.customer.dto.Customer;
import com.digitalcoffee.customer.dto.CustomerDetails;
import com.digitalcoffee.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{username}")
    public CustomerDetails getCustomerByUsername(@PathVariable("username") String username) {
        log.info("called getCustomer of CustomerController");
        return customerService.getCustomerByUsername(username);
    }

    @PutMapping("/{username}")
    public CustomerDetails updateCustomer(
            @PathVariable("username") String username,
            @RequestBody Customer customer) {
        customer.setUsername(username);
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/get-all-customers")
    public List<CustomerDetails> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("")
    public void createCustomer(@RequestParam("username") String username) {
        customerService.createCustomer(username);
    }
}
