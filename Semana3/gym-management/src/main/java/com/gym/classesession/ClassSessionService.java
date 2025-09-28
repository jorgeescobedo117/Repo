package com.gym.classesession;

import com.gym.events.ClassBookedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassSessionService {
    private final ClassSessionRepository repo;
    private final ApplicationEventPublisher publisher;

    public ClassSessionService(ClassSessionRepository repo, ApplicationEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    public ClassSession create(ClassSession c) { return repo.save(c); }

    public ClassSession book(Long classId, String memberName) {
        ClassSession c = repo.findById(classId).orElseThrow();
        // in a real app check capacity, etc.
        publisher.publishEvent(new ClassBookedEvent(this, c.getName(), memberName));
        return c;
    }

    public List<ClassSession> list() { return repo.findAll(); }
}
