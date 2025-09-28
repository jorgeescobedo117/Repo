package com.gym.equipment;

import com.gym.events.EquipmentMaintenanceEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository repo;
    private final ApplicationEventPublisher publisher;

    public EquipmentService(EquipmentRepository repo, ApplicationEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    public Equipment create(Equipment e) { return repo.save(e); }

    public Equipment reportMaintenance(Long id, String issue) {
        Equipment e = repo.findById(id).orElseThrow();
        e.setStatus("MAINTENANCE");
        Equipment saved = repo.save(e);
        publisher.publishEvent(new EquipmentMaintenanceEvent(this, saved.getName(), issue));
        return saved;
    }

    public List<Equipment> list() { return repo.findAll(); }
}
