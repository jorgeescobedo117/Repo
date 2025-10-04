package com.example.gym.access;

import com.example.gym.shared.events.AccessGrantedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/access")
public class AccessController {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<AccessRecord> grant(@RequestParam Long memberId){
        AccessRecord r = new AccessRecord(memberId);
        AccessRecord saved = accessRepository.save(r);
        publisher.publishEvent(new AccessGrantedEvent(this, memberId));
        return ResponseEntity.ok(saved);
    }
}
