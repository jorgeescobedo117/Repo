package com.gym.equipment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) { this.service = service; }

    @PostMapping
    public Equipment create(@RequestBody Equipment e) { return service.create(e); }

    @PostMapping("/{id}/maintenance")
    public Equipment maintenance(@PathVariable Long id, @RequestParam String issue) { return service.reportMaintenance(id, issue); }

    @GetMapping
    public List<Equipment> list() { return service.list(); }
}
