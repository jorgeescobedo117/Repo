package com.example.gym.membership;

public class MembershipActivatedEvent {
    private final Long memberId;

    public MembershipActivatedEvent(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
