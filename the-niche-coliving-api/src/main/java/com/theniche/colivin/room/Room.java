package com.theniche.colivin.room;

import com.theniche.colivin.common.domain.BaseEntity;
import com.theniche.colivin.house.House;
import com.theniche.colivin.roomassignment.AssignmentStatus;
import com.theniche.colivin.roomassignment.RoomAssignment;
import com.theniche.colivin.tenants.Tenant;
import com.theniche.colivin.util.CodeGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

    @Column(nullable = false)
    private String roomNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "room_type")
    private RoomType roomType;
    @Column(nullable = false,unique = true,updatable = false,name = "room_code")
    private String roomCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "room_status")
    private RoomStatus roomStatus = RoomStatus.AVAILABLE;
    @Column(nullable = false)
    private Integer capacity;
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "house_id")
    private House house;


    @OneToMany(mappedBy = "room",cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
    private Set<RoomAssignment> roomAssignments = new HashSet<>();


    public Room(){}
    public Room(String roomNumber,RoomType roomType,Integer capacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
    }

    // helper method
    public boolean hasCapacity(){
        long currentOccupants = roomAssignments.stream()
                .filter(ra->ra.getAssignmentStatus() == AssignmentStatus.ACTIVE)
                .count();
        return currentOccupants > capacity;
    }
    public Set<Tenant> getCurrentTenants(){
        return roomAssignments.stream()
                .filter((ra)-> ra.getAssignmentStatus() == AssignmentStatus.ACTIVE)
                .map(RoomAssignment::getTenant)
                .collect(Collectors.toSet());
    }
    @PrePersist
    public void prePersist(){
        if(this.roomCode == null) {
            this.roomCode = CodeGenerator.generateRoomCode();
        }
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Room setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Room setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public Room setRoomCode(String roomCode) {
        this.roomCode = roomCode;
        return this;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public Room setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Room setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Room setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Set<RoomAssignment> getRoomAssignments() {
        return roomAssignments;
    }
}
