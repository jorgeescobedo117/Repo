package com.gym.access;

import com.gym.events.AccessGrantedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccessService {
    private final AccessLogRepository repo;
    private final ApplicationEventPublisher publisher;

    public AccessService(AccessLogRepository repo, ApplicationEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    public AccessLog grant(String memberName, String door) {
        AccessLog log = AccessLog.builder().memberName(memberName).door(door).timestamp(LocalDateTime.now()).build();
        AccessLog saved = repo.save(log);
        publisher.publishEvent(new AccessGrantedEvent(this, memberName, door));
        return saved;
    }

    public List<AccessLog> list() { return repo.findAll(); }
}
