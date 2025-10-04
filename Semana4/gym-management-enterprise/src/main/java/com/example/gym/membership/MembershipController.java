package com.example.gym.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping
    public ResponseEntity<Member> create(@RequestParam String name, @RequestParam(required = false) BigDecimal fee) {
        if (fee == null) fee = BigDecimal.valueOf(29.99);
        Member m = membershipService.createMember(name, fee);
        return ResponseEntity.created(URI.create("/api/members/" + m.getId())).body(m);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<Member> activate(@PathVariable Long id) {
        Optional<Member> memberOpt = membershipService.activateMember(id);
        if (memberOpt.isPresent()) {
            return ResponseEntity.ok(memberOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(membershipService.getAllMembers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestParam String name,
                                         @RequestParam(required = false) BigDecimal fee) {
        Optional<Member> memberOpt = membershipService.updateMember(id, name, fee);
        if (memberOpt.isPresent()) {
            return ResponseEntity.ok(memberOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = membershipService.deleteMember(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(membershipService.getAllSubscriptions());
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<Subscription> createSubscription(@RequestParam String name,
                                                           @RequestParam BigDecimal price) {
        Subscription s = membershipService.createSubscription(name, price);
        return ResponseEntity.created(URI.create("/api/members/subscriptions/" + s.getId())).body(s);
    }
}
