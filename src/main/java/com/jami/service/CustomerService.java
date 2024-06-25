package com.jami.service;

import com.jami.model.customer.Customer;
import com.jami.model.response.ApiResponse;
import com.jami.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<ApiResponse> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        ApiResponse response;
        if (!customers.isEmpty()) {
            response = new ApiResponse(true, "Data Found", customerRepository.findAll());
            return ResponseEntity.ok(response);
        } else {
            response = new ApiResponse(false, "Data Not Found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    public ResponseEntity<ApiResponse> addCustomer(Customer customer) {
        if (customer != null) {
            customerRepository.save(customer);
            ApiResponse response = new ApiResponse(true, "Customer Added Successfully", customer);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Please Insert Customer");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    public ResponseEntity<ApiResponse> deleteCustomerById(Integer customerId) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);
        ApiResponse response;
        if (existingCustomerOptional.isPresent()) {
            customerRepository.deleteById(customerId);
            response = new ApiResponse(true, "Customer Deleted Successfully");
        } else {
            response = new ApiResponse(false, "Customer not found");
        }
        return ResponseEntity.ok(response);
    }


    public ResponseEntity<ApiResponse> updateCustomerById(Integer customerId, Customer customer) {
        // Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            if (customer.getName() != null) {
                existingCustomer.setName(customer.getName());
            }
            if (customer.getEmail() != null) {
                existingCustomer.setEmail(customer.getEmail());
            }
            if (customer.getAge() != null) {
                existingCustomer.setAge(customer.getAge());
            }
            customerRepository.save(existingCustomer);

            ApiResponse response = new ApiResponse(true, "Customer Updated Successfully", existingCustomer);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Customer not found");
            return ResponseEntity.ok(response);
        }
    }

    public ResponseEntity<ApiResponse> getCustomerById(Integer customerId) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);
        ApiResponse response;
        if (existingCustomerOptional.isPresent()) {
            response = new ApiResponse(true, "Customer Found", existingCustomerOptional.get());
        } else {
            response = new ApiResponse(false, "Customer not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
//        return existingCustomerOptional.orElse(null);
    }


}
