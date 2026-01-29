package com.theniche.colivin.roomassignment;

import com.theniche.colivin.common.domain.BaseEntity;
import com.theniche.colivin.house.House;
import com.theniche.colivin.room.Room;
import com.theniche.colivin.tenants.Tenant;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "room_assignments",
        indexes = {
                @Index(
                        name = "idx_assignment_house_status",
                        columnList = "house_id, assignmentStatus"
                ),
                @Index(
                        name = "idx_assignment_room_status",
                        columnList = "room_id, assignmentStatus"
                ),
                @Index(
                        name = "idx_assignment_tenant_status",
                        columnList = "tenant_id, assignmentStatus"
                )
        }
)
public class RoomAssignment extends BaseEntity {

    public enum DepositStatus {
        HELD,
        PARTIALLY_RETURNED,
        RETURNED
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id",nullable = false)
    private House house;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id",nullable = false)
    private Tenant tenant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @Column(name = "lease_start_date")
    private LocalDate leaseStartDate;
    @Column(name="lease_end_date")
    private LocalDate leaseEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_status")
    private AssignmentStatus assignmentStatus;
    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_status", nullable = false)
    private DepositStatus depositStatus;
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

    public House getHouse() {
        return house;
    }

    public RoomAssignment setHouse(House house) {
        this.house = house;
        return this;
    }

    public RoomAssignment setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public RoomAssignment setRoom(Room room) {
        this.room = room;
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

    public RoomAssignment setSecurityDeposit(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
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


    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public String getNotes() {
        return notes;
    }


    public RoomAssignment setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
        return this;
    }

    public DepositStatus getDepositStatus() {
        return depositStatus;
    }

    public RoomAssignment setDepositStatus(DepositStatus depositStatus) {
        this.depositStatus = depositStatus;
        return this;
    }
}
