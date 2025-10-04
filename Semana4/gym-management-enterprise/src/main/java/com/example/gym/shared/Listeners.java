package com.example.gym.shared;

import com.example.gym.shared.events.AccessGrantedEvent;
import com.example.gym.shared.events.InvoiceGeneratedEvent;
import com.example.gym.shared.events.MembershipActivatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Listeners {

    @EventListener
    public void onMembershipActivated(MembershipActivatedEvent e){
        System.out.println("[Shared.Listener] membership activated: " + e.getMemberId());
    }

    @EventListener
    public void onAccessGranted(AccessGrantedEvent e){
        System.out.println("[Shared.Listener] access granted: " + e.getMemberId());
    }

    @EventListener
    public void onInvoiceGenerated(InvoiceGeneratedEvent e){
        System.out.println("[Shared.Listener] invoice generated: " + e.getInvoiceId());
    }
}
