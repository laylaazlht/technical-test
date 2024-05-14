package javaspring.technicaltest.core.api.admin;

import io.swagger.annotations.ApiOperation;
import javaspring.technicaltest.core.model.Customer;
import javaspring.technicaltest.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/v1/customer")
public class CustomerAdminApiV1 {

    @Autowired
    CustomerService service;

    @GetMapping()
    @ApiOperation("List")
    public Page<Customer> list(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));
        return service.findAll(pageable);
    }

    @GetMapping("/{code}")
    public Customer get(@PathVariable String code) {
        return service.findByCode(code);
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return service.persist(customer, new Customer());
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        var stored = service.findByCode(customer.getCode());
        service.persist(customer, stored);

        return stored;
    }

    @DeleteMapping()
    public void delete(@RequestParam String code){
        service.delete(code);
    }
}
