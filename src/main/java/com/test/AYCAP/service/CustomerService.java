package com.test.AYCAP.service;

import com.test.AYCAP.Entity.Customer;
import com.test.AYCAP.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.util.jar.Pack200.Packer.ERROR;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    interface Functional {
        public String getMemberTypeBySalary(int slary);

    }

    private final String PLATINUM = "Platinum";
    private final String GOLD = "Gold";
    private final String SILVER = "Silver";

    public Customer getUser(String username) {
        return customerRepository.findByUsername(username);
    }

    public Iterable<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    public Customer  addNewCustomer(Customer customer) throws Exception  {
        String memberType = "";
        String referenceCode = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH).format(LocalDateTime.now());

        Functional functional = salary -> {
            if (salary > 50000)
                return PLATINUM;
            else if (salary >= 30000 && salary <= 50000)
                return GOLD;
            else if (salary >= 15000 && salary < 30000)
                return SILVER;
            else
                return ERROR;
        };
        try {
            referenceCode += customer.getPhone().substring(6);
            customer.setReferenceCode(referenceCode);
            memberType = functional.getMemberTypeBySalary(customer.getSalary());
            if (ERROR.equals(memberType))
                throw new Exception("salary < 15000");
            customer.setMemberType(memberType);
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
