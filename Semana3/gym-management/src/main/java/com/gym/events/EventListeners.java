package com.gym.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {

    @EventListener
    public void onMembershipActivated(MembershipActivatedEvent e) {
        System.out.println("[EVENT] Membership activated: " + e.getMembership().getMemberName());
    }

    @EventListener
    public void onClassBooked(ClassBookedEvent e) {
        System.out.println("[EVENT] Class booked: " + e.getClassName() + " by " + e.getMemberName());
    }

    @EventListener
    public void onEquipmentMaintenance(EquipmentMaintenanceEvent e) {
        System.out.println("[EVENT] Equipment maintenance: " + e.getEquipmentName() + " issue: " + e.getIssue());
    }

    @EventListener
    public void onAccessGranted(AccessGrantedEvent e) {
        System.out.println("[EVENT] Access granted to: " + e.getMemberName() + " door: " + e.getDoor());
    }
}
