package com.gym.membership;

import com.gym.events.MembershipActivatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {
    private final MembershipRepository repository;
    private final ApplicationEventPublisher publisher;

    public MembershipService(MembershipRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public Membership activate(Membership m) {
        m.setActive(true);
        Membership saved = repository.save(m);
        publisher.publishEvent(new MembershipActivatedEvent(this, saved));
        return saved;
    }

    public Membership create(Membership m) {
        m.setActive(false);
        return repository.save(m);
    }

    public List<Membership> list() { return repository.findAll(); }
}
