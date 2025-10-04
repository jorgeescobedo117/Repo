package com.example.gym.access;

import com.example.gym.shared.events.AccessGrantedEvent;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccessControllerTest {

    @Mock
    private AccessRepository accessRepository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private AccessController accessController;

    public AccessControllerTest(){ MockitoAnnotations.openMocks(this); }

    @Test
    public void grantAccessSavesAndPublishes(){
        AccessRecord r = new AccessRecord(1L);
        when(accessRepository.save(any(AccessRecord.class))).thenReturn(r);

        ResponseEntity<AccessRecord> resp = accessController.grant(1L);
        assertEquals(200, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        verify(publisher, times(1)).publishEvent(any(AccessGrantedEvent.class));
    }
}
