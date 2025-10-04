package com.example.gym.access;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Setter
@Getter
@Document(collection = "access_records")
public class AccessRecord {
    @Id
    private String id;
    private Long memberId;
    private Instant timestamp;

    public AccessRecord(Long memberId){
        this.memberId = memberId;
        this.timestamp = Instant.now();
    }

}
