package com.example.gym.billing;

import com.example.gym.membership.Member;
import com.example.gym.membership.MemberRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final MemberRepository memberRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public BatchConfig(MemberRepository memberRepository, InvoiceRepository invoiceRepository) {
        this.memberRepository = memberRepository;
        this.invoiceRepository = invoiceRepository;
    }


    @Bean
    public ItemReader<Member> memberItemReader() {
        return new ItemReader<>() {
            private Iterator<Member> iterator;

            @Override
            public Member read() {
                if (iterator == null) {
                    List<Member> actives = memberRepository.findByActiveTrue();
                    iterator = actives.iterator();
                }
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }


    @Bean
    public ItemProcessor<Member, Invoice> memberToInvoiceProcessor() {
        return member -> {
            BigDecimal amount = member.getMonthlyFee() != null
                    ? member.getMonthlyFee()
                    : BigDecimal.valueOf(29.99);
            return new Invoice(member.getId(), amount);
        };
    }

    @Bean
    public ItemWriter<Invoice> invoiceItemWriter() {
        return invoiceRepository::saveAll;
    }


    @Bean
    public Step billingStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("billingStep", jobRepository)
                .<Member, Invoice>chunk(10, transactionManager)
                .reader(memberItemReader())
                .processor(memberToInvoiceProcessor())
                .writer(invoiceItemWriter())
                .build();
    }


    @Bean
    public Job billingJob(JobRepository jobRepository, Step billingStep) {
        return new JobBuilder("billingJob", jobRepository)
                .start(billingStep)
                .build();
    }
}
