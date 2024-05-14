package javaspring.technicaltest.core.repository;

import javaspring.technicaltest.core.model.Cart;
import javaspring.technicaltest.core.model.Customer;
import javaspring.technicaltest.core.model.enumeration.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCode(String code);

    List<Cart> findByCustomer(Customer customer);

}
