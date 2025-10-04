package com.example.gym.billing;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BillingControllerTest {

    @Mock
    private Job billingJob;

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private BillingController billingController;

    public BillingControllerTest(){ MockitoAnnotations.openMocks(this); }

    @Test
    public void runJobPublishesEvent() throws Exception {
        // Simulate job launcher returning a JobExecution
        when(jobLauncher.run(any(Job.class), any())).thenAnswer(invocation -> {
            class JE extends org.springframework.batch.core.JobExecution {
                public JE(){ super(1L); setStatus(org.springframework.batch.core.BatchStatus.COMPLETED); }
            }
            JE je = new JE();
            return je;
        });

        ResponseEntity<String> resp = billingController.runJob();
        assertEquals(200, resp.getStatusCodeValue());
        verify(publisher, times(1)).publishEvent(any());
    }
}
