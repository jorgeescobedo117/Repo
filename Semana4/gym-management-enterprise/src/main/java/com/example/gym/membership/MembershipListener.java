package com.example.gym.membership;

import com.example.gym.shared.events.MembershipActivatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MembershipListener {

    @EventListener
    public void handle(MembershipActivatedEvent event) {
        System.out.println("[MembershipListener] Membership activated for: " + event.getMemberId());
    }
}
