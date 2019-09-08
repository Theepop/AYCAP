package com.test.AYCAP.controller;


import com.test.AYCAP.Entity.Customer;
import com.test.AYCAP.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/rest")
public class MainController {
    @Autowired
    private CustomerService customerService;


    @PostMapping(path = "/add")
    @ResponseBody
    public Customer addNewCustomer(@RequestBody Customer c ) throws Exception   {
        return customerService.addNewCustomer(c);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Customer> getAllUsers() {
        return customerService.getAllUsers();
    }

    @GetMapping(path = "/{username}")
    public @ResponseBody
    Customer getUser(@PathVariable String username) {
        return customerService.getUser(username);
    }


}
