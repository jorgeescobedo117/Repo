package com.example.gym.shared.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InvoiceGeneratedEvent extends ApplicationEvent {
    private final Long invoiceId;
    public InvoiceGeneratedEvent(Object source, Long invoiceId) {
        super(source);
        this.invoiceId = invoiceId;
    }
}
