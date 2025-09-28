package com.gym.billing;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {
    private final InvoiceRepository repo;

    public BillingService(InvoiceRepository repo) { this.repo = repo; }

    public Invoice create(Invoice i) { return repo.save(i); }

    public Invoice pay(Long id) {
        Invoice inv = repo.findById(id).orElseThrow();
        inv.setPaid(true);
        return repo.save(inv);
    }

    public List<Invoice> list() { return repo.findAll(); }
}
