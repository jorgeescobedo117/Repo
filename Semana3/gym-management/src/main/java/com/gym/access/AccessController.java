package com.gym.access;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/access")
public class AccessController {
    private final AccessService service;

    public AccessController(AccessService service) { this.service = service; }

    @PostMapping("/grant")
    public AccessLog grant(@RequestParam String member, @RequestParam String door) { return service.grant(member, door); }

    @GetMapping
    public List<AccessLog> list() { return service.list(); }
}
