package javaspring.technicaltest.core.model.support;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import javaspring.technicaltest.core.api.support.Views;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class WithCodeAndName extends WithCode{

    @JsonView(Views.Nats.class)
    public String name;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public OffsetDateTime createdAt;

}
