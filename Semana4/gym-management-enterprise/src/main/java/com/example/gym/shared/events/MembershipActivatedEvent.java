package com.example.gym.shared.events;

import org.springframework.context.ApplicationEvent;

public class MembershipActivatedEvent extends ApplicationEvent {
    private Long memberId = 0L;
    public MembershipActivatedEvent(Object source) {
        super(source);
        this.memberId = memberId;
    }
    public Long getMemberId() { return memberId; }
}
