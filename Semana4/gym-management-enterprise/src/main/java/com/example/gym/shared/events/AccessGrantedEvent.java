package com.example.gym.shared.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AccessGrantedEvent extends ApplicationEvent {
    private final Long memberId;
    public AccessGrantedEvent(Object source, Long memberId) {
        super(source);
        this.memberId = memberId;
    }
}
