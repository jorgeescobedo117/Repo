package com.example.gym.billing;

import com.example.gym.shared.events.InvoiceGeneratedEvent;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private Job billingJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/run")
    public ResponseEntity<String> runJob() throws Exception{
        JobExecution je = jobLauncher.run(billingJob, new JobParametersBuilder()
                .addDate("runAt", new Date()).toJobParameters());
        // after job, inform (for simplicity publish for each invoice is omitted)
        publisher.publishEvent(new InvoiceGeneratedEvent(this, 0L));
        return ResponseEntity.ok("Job status: " + je.getStatus().toString());
    }
}
