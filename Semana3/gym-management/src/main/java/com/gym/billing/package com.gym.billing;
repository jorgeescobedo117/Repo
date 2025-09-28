package com.gym.billing;

import com.gym.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice extends BaseEntity {
    private String memberName;
    private LocalDate dueDate;
    private double amount;
    private boolean paid;
}
