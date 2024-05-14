package javaspring.technicaltest.core.service;

import javaspring.technicaltest.core.api.model.CartDto;
import javaspring.technicaltest.core.api.model.TransactionCartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {
    void addToCart(CartDto passed);
    Page<TransactionCartDto> findAllCarts(Pageable pageable);
    TransactionCartDto findCartByCode(String cartCode);
}
