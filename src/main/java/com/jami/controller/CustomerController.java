package com.jami.controller;

import com.jami.model.Customer;
import com.jami.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("list")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }


//    @PostMapping("add")
//    public void addCustomer(@RequestBody NewCustomerRequest request) {
//        Customer customer = new Customer();
//        customer.setName(request.name());
//        customer.setEmail(request.email());
//        customer.setAge(request.age());
//        customerService.addCustomer(customer);
//
//    }

    @PostMapping("add")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @DeleteMapping("delete/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomerById(id);
    }

    @PutMapping("update/{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer customer) {
        customer.setId(id);
        customerService.updateCustomerById(id, customer);
    }
}
