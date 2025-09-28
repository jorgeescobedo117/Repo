package com.gym.events;

import com.gym.membership.Membership;
import org.springframework.context.ApplicationEvent;

public class MembershipActivatedEvent extends ApplicationEvent {
    private final Membership membership;

    public MembershipActivatedEvent(Object source, Membership membership) {
        super(source);
        this.membership = membership;
    }

    public Membership getMembership() { return membership; }
}
