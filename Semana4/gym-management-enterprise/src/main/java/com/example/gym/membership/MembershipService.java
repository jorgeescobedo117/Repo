package com.example.gym.membership;

import com.example.gym.shared.events.MembershipActivatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Member createMember(String name, BigDecimal fee) {
        Member m = new Member(name, fee);
        return memberRepository.save(m);
    }

    public Optional<Member> activateMember(Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setActive(true);
                    Member saved = memberRepository.save(member);
                    eventPublisher.publishEvent(new MembershipActivatedEvent(saved.getId()));
                    return saved;
                });
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> updateMember(Long id, String name, BigDecimal fee) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setName(name);
                    if (fee != null) member.setMonthlyFee(fee);
                    return memberRepository.save(member);
                });
    }

    public boolean deleteMember(Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    memberRepository.delete(member);
                    return true;
                }).orElse(false);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription createSubscription(String name, BigDecimal price) {
        Subscription s = new Subscription(name, price);
        return subscriptionRepository.save(s);
    }
}
