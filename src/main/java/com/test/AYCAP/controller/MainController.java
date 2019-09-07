package com.test.AYCAP.controller;


import com.test.AYCAP.repository.CustomerRepository;
import com.test.AYCAP.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
@RequestMapping(path="/rest")
public class MainController {
    @Autowired
    CustomerRepository customerRepository;

    private final String PLATINUM = "Platinum";
    private final String GOLD = "Gold";
    private final String SILVER = "Silver";

    @PostMapping(path = "/add")
    public @ResponseBody String addNewCustomer(@RequestBody Customer c

    ){
        int salaryI=0;
        String memberType="";
        String referenceCode = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH).format(LocalDateTime.now());

        Customer customer = new Customer();
        try{
            customer.setUsername(c.getUsername());
            customer.setPassword(c.getPassword());
            customer.setAddress(c.getAddress());
            customer.setPhone(c.getPhone());

            referenceCode+=c.getPhone().substring(6);
            customer.setReferenceCode(referenceCode);
            salaryI= c.getSalary();
            if(salaryI>50000)
                memberType=PLATINUM;
            else if(salaryI>=30000&&salaryI<=50000)
                memberType=GOLD;
            else if (salaryI>=15000&&salaryI<30000)
                memberType=SILVER;
            else
                return "error";


            customer.setMemberType(memberType);
            customer.setSalary(salaryI);
            customerRepository.save(customer);
            return "save";
        }catch (Exception e){
            return "error";
        }

    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

}
