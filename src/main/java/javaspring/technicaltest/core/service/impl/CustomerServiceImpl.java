package javaspring.technicaltest.core.service.impl;

import javaspring.technicaltest.core.model.Customer;
import javaspring.technicaltest.core.repository.CustomerRepository;
import javaspring.technicaltest.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public Customer persist(Customer from, Customer to) {
        to.setCode(UUID.randomUUID().toString());
        to.setName(from.getName());
        to.setAddress(from.getAddress());
        to.setActive(from.isActive());
        repository.save(to);

        return to;
    }

    @Override
    public void delete(String code) {
        var customer = repository.findByCode(code);
        customer.setActive(false);

        repository.save(customer);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Customer findByCode(String code) {
        return repository.findByCode(code);
    }

}
