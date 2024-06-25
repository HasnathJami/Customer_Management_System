package com.jami.controller;

import com.jami.model.customer.Customer;
import com.jami.model.response.ApiResponse;
import com.jami.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("{customerId}")
    public ResponseEntity<ApiResponse> getCustomer(@PathVariable("customerId") Integer id) {
        return customerService.getCustomerById(id);
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
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("delete/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customerId") Integer id) {
        return customerService.deleteCustomerById(id);
    }

    @PutMapping("update/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer customer) {
        return customerService.updateCustomerById(id, customer);
    }
}
