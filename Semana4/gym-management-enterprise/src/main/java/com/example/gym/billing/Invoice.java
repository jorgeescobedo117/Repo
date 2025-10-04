package com.example.gym.billing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private BigDecimal amount;
    private Instant createdAt;

    public Invoice() {}

    public Invoice(Long memberId, BigDecimal amount){
        this.memberId = memberId;
        this.amount = amount;
        this.createdAt = Instant.now();
    }
}
