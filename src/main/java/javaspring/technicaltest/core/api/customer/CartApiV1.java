package javaspring.technicaltest.core.api.customer;

import javaspring.technicaltest.core.api.model.CartDto;
import javaspring.technicaltest.core.api.model.TransactionCartDto;
import javaspring.technicaltest.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartApiV1 {

    @Autowired
    CartService service;

    @PostMapping
    public void add(@RequestBody CartDto passed) {
        service.addToCart(passed);
    }

    @GetMapping
    public Page<TransactionCartDto> getAllCarts(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));
        return service.findAllCarts(pageable);
    }

    @GetMapping("/{code}")
    public TransactionCartDto getCartByCode(@PathVariable("code") String code) {
        return service.findCartByCode(code);
    }

}
