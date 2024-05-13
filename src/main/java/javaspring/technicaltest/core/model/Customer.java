package javaspring.technicaltest.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import javaspring.technicaltest.core.model.support.WithCodeAndName;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "customer_uk", columnNames = {"code"}),
})
@Getter
@Setter
public class Customer extends WithCodeAndName {

    @Column(length = 5000)
    private String address;

}
