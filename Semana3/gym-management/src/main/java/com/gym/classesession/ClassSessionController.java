package com.gym.classesession;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassSessionController {
    private final ClassSessionService service;

    public ClassSessionController(ClassSessionService service) { this.service = service; }

    @PostMapping
    public ClassSession create(@RequestBody ClassSession c) { return service.create(c); }

    @PostMapping("/{id}/book")
    public ClassSession book(@PathVariable Long id, @RequestParam String member) { return service.book(id, member); }

    @GetMapping
    public List<ClassSession> list() { return service.list(); }
}
