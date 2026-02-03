package com.theniche.colivin;

import com.theniche.colivin.common.domain.BaseEntity;
import com.theniche.colivin.room.Room;
import com.theniche.colivin.tenants.Tenant;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    public enum PaymentStatus{
        PAID,
        PENDING,
        LATE
    }

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

    public Tenant getTenant() {
        return tenant;
    }

    public Room getRoom() {
        return room;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }
}
