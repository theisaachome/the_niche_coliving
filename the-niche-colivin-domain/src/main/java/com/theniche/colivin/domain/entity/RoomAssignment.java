package com.theniche.colivin.domain.entity;

import com.theniche.colivin.domain.common.AssignmentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "room_assignments")
public class RoomAssignment extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id",nullable = false)
    private Tenant tenant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @Column(name = "assignment_date",nullable = false)
    private LocalDate assignmentDate;
    @Column(name = "lease_start_date")
    private LocalDate leaseStartDate;
    @Column(name="lease_end_date")
    private LocalDate leaseEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_status")
    private AssignmentStatus assignmentStatus;
    @Column(name = "security_deposit")
    private BigDecimal securityDeposit;
    @Column(name = "notes")
    private String notes;

    // business logic methods
    public  boolean isActive(){
        return this.assignmentStatus == AssignmentStatus.ACTIVE;
    }
    public boolean isExpired(){
        return  this.leaseEndDate !=null && leaseEndDate.isBefore(LocalDate.now());
    }

    public long getDaysRemaining(){
        if(leaseEndDate ==null) return  -1;
        return ChronoUnit.DAYS.between(leaseEndDate, LocalDate.now());
    }

    public RoomAssignment setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public RoomAssignment setRoom(Room room) {
        this.room = room;
        return this;
    }

    public RoomAssignment setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
        return this;
    }

    public RoomAssignment setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
        return this;
    }

    public RoomAssignment setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
        return this;
    }

    public RoomAssignment setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    public RoomAssignment setSecurityDeposit(BigDecimal securityDeposit) {
        this.securityDeposit = securityDeposit;
        return this;
    }

    public RoomAssignment setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public BigDecimal getSecurityDeposit() {
        return securityDeposit;
    }

    public String getNotes() {
        return notes;
    }
}
