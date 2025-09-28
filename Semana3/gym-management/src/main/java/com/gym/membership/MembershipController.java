package com.gym.membership;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {
    private final MembershipService service;

    public MembershipController(MembershipService service) {
        this.service = service;
    }

    @PostMapping
    public Membership create(@RequestBody Membership m) { return service.create(m); }

    @PostMapping("/activate")
    public Membership activate(@RequestBody Membership m) { return service.activate(m); }

    @GetMapping
    public List<Membership> list() { return service.list(); }
}
