package javaspring.technicaltest.core.api.customer;

import io.swagger.annotations.ApiOperation;
import javaspring.technicaltest.core.model.Product;
import javaspring.technicaltest.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductApiV1 {

    @Autowired
    ProductService service;

    @GetMapping()
    @ApiOperation("List")
    public Page<Product> list(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));
        return service.findAll(pageable);
    }

    @GetMapping("/{code}")
    public Product get(@PathVariable String code) {
        return service.findByCode(code);
    }
}
