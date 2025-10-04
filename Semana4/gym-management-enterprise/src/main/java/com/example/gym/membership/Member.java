package com.example.gym.membership;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal monthlyFee;
    private boolean active = false;

    public Member() {}

    public Member(String name, BigDecimal monthlyFee) {
        this.name = name;
        this.monthlyFee = monthlyFee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getMonthlyFee() { return monthlyFee; }
    public void setMonthlyFee(BigDecimal monthlyFee) { this.monthlyFee = monthlyFee; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
