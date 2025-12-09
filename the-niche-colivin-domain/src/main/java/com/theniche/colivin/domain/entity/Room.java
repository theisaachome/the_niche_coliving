package com.theniche.colivin.domain.entity;
import com.theniche.colivin.domain.common.AssignmentStatus;
import com.theniche.colivin.domain.common.CodeGenerator;
import com.theniche.colivin.domain.common.RoomStatus;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity{

    @Column(nullable = false)
    private String roomNumber;
    @Column(nullable = false,unique = true,updatable = false,name = "room_code")
    private String roomCode;
    @Column(nullable = false)
    private Integer capacity;
    private String notes;
    @Column(name = "is_available")
    private Boolean isAvailable = true;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "room_status")
    private RoomStatus roomStatus = RoomStatus.VACANT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(mappedBy = "room",cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
    private Set<RoomAssignment> roomAssignments = new HashSet<>();

    public Room assignToHouse(House house) {
        this.house = house;
        return this;
    }

    public Room() {}

    public Room(String roomNumber, Integer capacity, String notes) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.notes = notes;
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
    public void prePersis(){
        if(this.roomCode==null){
            this.roomCode = CodeGenerator.generateRoomCode();
        }
    }

    public Room setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public Room setRoomCode(String roomCode) {
        this.roomCode = roomCode;
        return this;
    }

    public Room setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public Room setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Room setAvailable(Boolean available) {
        isAvailable = available;
        return this;
    }

    public Room setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
        return this;
    }

    public Room setHouse(House house) {
        this.house = house;
        return this;
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getNotes() {
        return notes;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public House getHouse() {
        return house;
    }

    public Set<RoomAssignment> getRoomAssignments() {
        return roomAssignments;
    }
}
