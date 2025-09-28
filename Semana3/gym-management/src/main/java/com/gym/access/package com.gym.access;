package com.gym.access;

import com.gym.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessLog extends BaseEntity {
    private String memberName;
    private LocalDateTime timestamp;
    private String door; // e.g., "Main"
}
