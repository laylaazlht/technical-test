package javaspring.technicaltest.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import javaspring.technicaltest.core.model.enumeration.CartStatus;
import javaspring.technicaltest.core.model.support.WithCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "cart_uk", columnNames = {"code"}),
})
@Getter
@Setter
public class Cart extends WithCode {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public OffsetDateTime createdAt;

    @ManyToOne(optional = false)
    private Customer customer;

    private CartStatus status;
}
