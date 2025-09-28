package com.gym.events;

import org.springframework.context.ApplicationEvent;

public class AccessGrantedEvent extends ApplicationEvent {
    private final String memberName;
    private final String door;

    public AccessGrantedEvent(Object source, String memberName, String door) {
        super(source);
        this.memberName = memberName;
        this.door = door;
    }

    public String getMemberName() { return memberName; }
    public String getDoor() { return door; }
}
