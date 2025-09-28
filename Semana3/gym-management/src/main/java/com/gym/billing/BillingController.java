package com.gym.billing;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
    private final BillingService service;

    public BillingController(BillingService service) { this.service = service; }

    @PostMapping
    public Invoice create(@RequestBody Invoice i) { return service.create(i); }

    @PostMapping("/{id}/pay")
    public Invoice pay(@PathVariable Long id) { return service.pay(id); }

    @GetMapping
    public List<Invoice> list() { return service.list(); }
}
