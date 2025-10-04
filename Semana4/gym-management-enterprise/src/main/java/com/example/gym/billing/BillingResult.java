package com.example.gym.billing;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BillingResult {

    private Long memberId;
    private String memberName;
    private BigDecimal monthlyFee;
    private BigDecimal discount;
    private BigDecimal finalFee;
    private LocalDate invoiceDate;
    private String status;

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public BigDecimal getMonthlyFee() { return monthlyFee; }
    public void setMonthlyFee(BigDecimal monthlyFee) { this.monthlyFee = monthlyFee; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getFinalFee() { return finalFee; }
    public void setFinalFee(BigDecimal finalFee) { this.finalFee = finalFee; }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
