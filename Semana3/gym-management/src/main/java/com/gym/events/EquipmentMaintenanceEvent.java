package com.gym.events;

import org.springframework.context.ApplicationEvent;

public class EquipmentMaintenanceEvent extends ApplicationEvent {
    private final String equipmentName;
    private final String issue;

    public EquipmentMaintenanceEvent(Object source, String equipmentName, String issue) {
        super(source);
        this.equipmentName = equipmentName;
        this.issue = issue;
    }

    public String getEquipmentName() { return equipmentName; }
    public String getIssue() { return issue; }
}
