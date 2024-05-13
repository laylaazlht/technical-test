package javaspring.technicaltest.core.model.support;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import javaspring.technicaltest.core.api.support.Views;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class WithCode {

    @Column(length = 100, unique = true) // Uniqueness set in each table to have explicit index name
    @JsonView(Views.Nats.class)
    private String code;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(Views.Nats.class)
    public Long getId() {
        return id;
    }
}
