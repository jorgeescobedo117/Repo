package com.example.gym.billing;

import com.example.gym.membership.Member;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@EnableBatchProcessing
public class BillingJobConfig {

    // Reader
    @Bean
    public JpaPagingItemReader<Member> reader(EntityManagerFactory emf) {
        return new JpaPagingItemReaderBuilder<Member>()
                .name("memberReader")
                .entityManagerFactory(emf)
                .queryString("SELECT m FROM Member m WHERE m.active = true")
                .pageSize(5)
                .build();
    }

    // Processor: aplica descuento y calcula mensualidad final
    @Bean
    public ItemProcessor<Member, BillingResult> processor() {
        return member -> {
            BillingResult result = new BillingResult();
            result.setMemberId(member.getId());
            result.setMemberName(member.getName());
            result.setMonthlyFee(member.getMonthlyFee());

            // Lógica de descuentos
            BigDecimal discount = BigDecimal.ZERO;
            if (member.getMonthlyFee().compareTo(new BigDecimal("700")) > 0) {
                discount = member.getMonthlyFee().multiply(new BigDecimal("0.15")); // 15%
            } else if (member.getMonthlyFee().compareTo(new BigDecimal("600")) > 0) {
                discount = member.getMonthlyFee().multiply(new BigDecimal("0.10")); // 10%
            }

            result.setDiscount(discount);
            result.setFinalFee(member.getMonthlyFee().subtract(discount));
            result.setInvoiceDate(LocalDate.now());
            result.setStatus(Math.random() > 0.5 ? "PAID" : "PENDING");

            System.out.println("Factura: " + result.getMemberName() +
                    " | Total: " + result.getFinalFee() +
                    " | Descuento: " + result.getDiscount() +
                    " | Estado: " + result.getStatus());

            return result;
        };
    }

    // Writer: guarda resultados en tabla billing_results
    @Bean
    public JdbcBatchItemWriter<BillingResult> writer(DataSource dataSource) {
        JdbcBatchItemWriter<BillingResult> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO billing_results " +
                "(member_id, member_name, monthly_fee, discount, final_fee, invoice_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");
        writer.setItemPreparedStatementSetter((billing, ps) -> {
            ps.setLong(1, billing.getMemberId());
            ps.setString(2, billing.getMemberName());
            ps.setBigDecimal(3, billing.getMonthlyFee());
            ps.setBigDecimal(4, billing.getDiscount());
            ps.setBigDecimal(5, billing.getFinalFee());
            ps.setDate(6, java.sql.Date.valueOf(billing.getInvoiceDate()));
            ps.setString(7, billing.getStatus());
        });
        return writer;
    }

    // Step
    @Bean
    public Step billingStep(JobRepository jobRepository,
                            JpaPagingItemReader<Member> reader,
                            ItemProcessor<Member, BillingResult> processor,
                            JdbcBatchItemWriter<BillingResult> writer) {
        return new StepBuilder("billingStep", jobRepository)
                .<Member, BillingResult>chunk(5, new ResourcelessTransactionManager())
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // Job
    @Bean
    public Job billingJob(JobRepository jobRepository, Step billingStep) {
        return new JobBuilder("billingJob", jobRepository)
                .start(billingStep)
                .build();
    }
}
