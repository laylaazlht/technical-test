package javaspring.technicaltest.core.service;

import javaspring.technicaltest.core.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer persist(Customer from, Customer to);

    void delete(String code);

    Page<Customer> findAll(Pageable pageable);

    Customer findByCode(String code);
}
