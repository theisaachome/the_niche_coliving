package com.theniche.colivin.domain.entity;

import com.theniche.colivin.domain.common.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment extends BaseEntity{

    @ManyToOne(optional = false)
    private Tenant tenant;
    @ManyToOne(optional = false)
    private Room room;

    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private LocalDateTime dueDate;
    private String referenceNumber;

}
