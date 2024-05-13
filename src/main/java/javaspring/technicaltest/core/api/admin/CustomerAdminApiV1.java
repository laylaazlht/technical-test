package javaspring.technicaltest.core.api.admin;

import javaspring.technicaltest.core.model.Customer;
import javaspring.technicaltest.core.repository.CustomerRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/v1/customer")
public class CustomerAdminApiV1 {

    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @GetMapping("/{code}")
    public Customer get(@PathVariable String code) {
        return customerRepository.findByCode(code);
    }

    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return persist(customer, new Customer());
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        var stored = customerRepository.findByCode(customer.getCode());
        persist(customer, stored);

        return stored;
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        Customer customer = customerRepository.findByCode(code);
        if (customer == null) {
            throw new RuntimeException("Customer not found with code: " + code);
        }
        customerRepository.delete(customer);
    }

    private Customer persist(Customer from, Customer to) {
        to.setCode(UUID.randomUUID().toString());
        to.setName(from.getName());
        to.setAddress(from.getAddress());
        customerRepository.save(to);

        return to;
    }

}
