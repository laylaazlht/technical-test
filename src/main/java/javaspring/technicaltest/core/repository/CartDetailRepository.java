package javaspring.technicaltest.core.repository;

import javaspring.technicaltest.core.model.Cart;
import javaspring.technicaltest.core.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    CartDetail findByCode(String code);

    List<CartDetail> findByCart(Cart cart);

}
