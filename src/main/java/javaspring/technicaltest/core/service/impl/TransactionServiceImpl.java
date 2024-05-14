package javaspring.technicaltest.core.service.impl;

import javaspring.technicaltest.core.api.model.TransactionDto;
import javaspring.technicaltest.core.model.Transaction;
import javaspring.technicaltest.core.model.TransactionDetail;
import javaspring.technicaltest.core.model.enumeration.CartStatus;
import javaspring.technicaltest.core.repository.CartRepository;
import javaspring.technicaltest.core.repository.CustomerRepository;
import javaspring.technicaltest.core.repository.ProductRepository;
import javaspring.technicaltest.core.repository.TransactionDetailRepository;
import javaspring.technicaltest.core.repository.TransactionRepository;
import javaspring.technicaltest.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Override
    public void placeOrder(TransactionDto passed) {
        var customer = customerRepository.findByCode(passed.getCustomerCode());
        if (customer == null) {
            throw new RuntimeException("Customer not found!");
        }

        var cart = cartRepository.findByCode(passed.getCartCode());
        if (cart == null) {
            throw new RuntimeException("Cart not found!");
        }

        var transaction = new Transaction();
        transaction.setCode(UUID.randomUUID().toString());
        transaction.setCustomer(customer);
        transaction.setCart(cart);

        var totalOrderPrice = BigDecimal.ZERO;

        for (TransactionDto.TransactionItem itemDto : passed.getTransactionItems()) {
            var product = productRepository.findByCode(itemDto.getProductCode());
            if (product == null) {
                throw new RuntimeException("Product not found!");
            }
            totalOrderPrice = totalOrderPrice.add(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
        }

        transaction.setTotal(totalOrderPrice);
        var savedTransaction = transactionRepository.save(transaction);

        for (TransactionDto.TransactionItem itemDto : passed.getTransactionItems()) {
            var product = productRepository.findByCode(itemDto.getProductCode());
            if (product == null) {
                throw new RuntimeException("Product not found!");
            }

            totalOrderPrice = totalOrderPrice.add(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));

            var transactionDetail = new TransactionDetail();
            transactionDetail.setCode(UUID.randomUUID().toString());
            transactionDetail.setTransaction(savedTransaction);
            transactionDetail.setProduct(product);
            transactionDetail.setQuantity(itemDto.getQuantity());
            transactionDetail.setTotal(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
            transactionDetailRepository.save(transactionDetail);
        }

        cart.setStatus(CartStatus.INACTIVE);
        cartRepository.save(cart);
    }
}
