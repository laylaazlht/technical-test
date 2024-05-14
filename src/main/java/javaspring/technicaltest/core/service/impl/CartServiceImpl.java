package javaspring.technicaltest.core.service.impl;

import javaspring.technicaltest.core.api.model.CartDto;
import javaspring.technicaltest.core.api.model.TransactionCartDto;
import javaspring.technicaltest.core.model.Cart;
import javaspring.technicaltest.core.model.CartDetail;
import javaspring.technicaltest.core.model.Customer;
import javaspring.technicaltest.core.model.enumeration.CartStatus;
import javaspring.technicaltest.core.repository.CartDetailRepository;
import javaspring.technicaltest.core.repository.CartRepository;
import javaspring.technicaltest.core.repository.CustomerRepository;
import javaspring.technicaltest.core.repository.ProductRepository;
import javaspring.technicaltest.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void addToCart(CartDto passed) {
        var customer = customerRepository.findByCode(passed.getCustomerCode());
        var product = productRepository.findByCode(passed.getProductCode());

        if (product.getQuantity() - passed.getQuantity() < 0) {
            throw new RuntimeException("ERROR QUANTITY EXCEED");
        }

        var cart = findOrCreateCartForCustomer(customer);
        var cartDetail = new CartDetail();
        cartDetail.setCode(UUID.randomUUID().toString());
        cartDetail.setCart(cart);
        cartDetail.setQuantity(passed.getQuantity());
        cartDetail.setProduct(product);
        cartDetail.setTotal(product.getPrice().multiply(BigDecimal.valueOf(passed.getQuantity())));
        cartDetailRepository.save(cartDetail);

        product.setQuantity(product.getQuantity() - passed.getQuantity());
        productRepository.save(product);
    }

    private Cart findOrCreateCartForCustomer(Customer customer) {
        var existingCarts = cartRepository.findByCustomer(customer);
        if (!existingCarts.isEmpty()) {
            return existingCarts.get(0);
        } else {
            var cart = new Cart();
            cart.setCode(UUID.randomUUID().toString());
            cart.setCustomer(customer);
            cart.setStatus(CartStatus.ACTIVE);
            return cartRepository.save(cart);
        }
    }

    public Page<TransactionCartDto> findAllCarts(Pageable pageable) {
        Page<Cart> cartsPage = cartRepository.findAll(pageable);
        return cartsPage.map(this::buildTransactionCart);
    }

    public TransactionCartDto findCartByCode(String cartCode) {
        Cart cart = cartRepository.findByCode(cartCode);
        if (cart == null) {
            throw new RuntimeException("Cart not found!");
        }
        return buildTransactionCart(cart);
    }

    private TransactionCartDto buildTransactionCart(Cart cart) {
        var customer = cart.getCustomer();
        var cartDto = new TransactionCartDto();
        cartDto.setCartCode(cart.getCode());
        cartDto.setCustomerCode(customer.getCode());
        cartDto.setCustomerName(customer.getName());
        cartDto.setCustomerAddress(customer.getAddress());
        List<TransactionCartDto.ProductItem> productItems = new ArrayList<>();

        var totalCart = BigDecimal.ZERO;
        List<CartDetail> cartDetails = cartDetailRepository.findByCart(cart);
        for (CartDetail cartDetail : cartDetails) {
            var product = cartDetail.getProduct();
            TransactionCartDto.ProductItem productItem = new TransactionCartDto.ProductItem();
            productItem.setProductCode(product.getCode());
            productItem.setProductName(product.getName());
            productItem.setType(product.getType());
            productItem.setPrice(product.getPrice());
            productItem.setQuantity(cartDetail.getQuantity());
            productItem.setSubTotal(cartDetail.getTotal());
            productItem.setActive(true);
            productItems.add(productItem);

            totalCart = totalCart.add(cartDetail.getTotal());
        }
        cartDto.setProductItems(productItems);
        cartDto.setTotal(totalCart);

        return cartDto;
    }
}
