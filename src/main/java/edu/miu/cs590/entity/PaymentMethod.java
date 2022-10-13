package edu.miu.cs590.entity;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "method", discriminatorType = DiscriminatorType.STRING)
public class PaymentMethod {
    @Id
    @GeneratedValue
    private long id;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    @Column(insertable = false, updatable = false)
    private String method;

    private String username;

    private boolean isDefault = false;
}
