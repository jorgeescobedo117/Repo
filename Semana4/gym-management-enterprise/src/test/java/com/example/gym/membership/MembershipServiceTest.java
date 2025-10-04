package com.example.gym.membership;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MembershipServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private MembershipService membershipService;

    public MembershipServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createMemberAndActivate() {
        Member m = new Member("TestUser", BigDecimal.valueOf(19.99));
        m.setId(1L);
        when(memberRepository.save(any(Member.class))).thenAnswer(i -> { Member mm = i.getArgument(0); mm.setId(1L); return mm; });
        Member saved = membershipService.createMember("TestUser", BigDecimal.valueOf(19.99));
        assertNotNull(saved);
        assertEquals("TestUser", saved.getName());

        when(memberRepository.findById(1L)).thenReturn(Optional.of(saved));
        Optional<Member> activated = membershipService.activateMember(1L);
        assertTrue(activated.isPresent());
        assertTrue(activated.get().isActive());
        verify(publisher, times(1)).publishEvent(any());
    }
}
