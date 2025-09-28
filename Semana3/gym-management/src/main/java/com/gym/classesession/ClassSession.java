package com.gym.classesession;

import com.gym.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSession extends BaseEntity {
    private String name;
    private String instructor;
    private String schedule; // e.g. "Mon 18:00"
    private int capacity;
}
