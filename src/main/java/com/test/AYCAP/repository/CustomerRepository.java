package com.test.AYCAP.repository;

import com.test.AYCAP.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,String> {
    public Customer findByUsername(String username);
}
