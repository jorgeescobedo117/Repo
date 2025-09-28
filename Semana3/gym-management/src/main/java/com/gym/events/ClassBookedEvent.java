package com.gym.events;

import org.springframework.context.ApplicationEvent;

public class ClassBookedEvent extends ApplicationEvent {
    private final String className;
    private final String memberName;

    public ClassBookedEvent(Object source, String className, String memberName) {
        super(source);
        this.className = className;
        this.memberName = memberName;
    }

    public String getClassName() { return className; }
    public String getMemberName() { return memberName; }
}
