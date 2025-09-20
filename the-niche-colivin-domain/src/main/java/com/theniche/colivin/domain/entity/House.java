package com.theniche.colivin.domain.entity;
import com.theniche.colivin.domain.common.CodeGenerator;
import com.theniche.colivin.domain.common.HouseStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="houses",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_HOUSE_NAME", columnNames = "name")
        }
)
public class House extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String name;
    @Column(unique = true,nullable = false,name = "house_code")
    private String houseCode;
    private String remark;
    @Column(name = "discontinued_at")
    private LocalDate discontinuedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "house_status")
    private HouseStatus houseStatus =HouseStatus.ACTIVE;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "house", orphanRemoval = true)
    private Set<Room> rooms =new HashSet<>();

    public House() {}

    public House(String name, String houseCode, String address, String description, String notes) {
        this.name = name;
        this.houseCode = houseCode;
        this.remark = notes;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setHouse(this);
    }
    public void removeRoom(Room room) {
        room.setHouse(null);
        this.rooms.remove(room);
    }

    public void removeRooms(){
        Iterator<Room> iterator = this.rooms.iterator();
        while (iterator.hasNext()) {
            var room = iterator.next();
            room.setHouse(null);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof House house)) return false;
        return Objects.equals(name, house.name);
    }

    @Override
    public int hashCode() {
        return 2025;
    }

    @PrePersist
    public void prePersist(){
        if(this.houseCode==null){
            this.houseCode = CodeGenerator.generateHouseCode();
        }
    }

    public House setName(String name) {
        this.name = name;
        return this;
    }

    public House setHouseCode(String houseCode) {
        this.houseCode = houseCode;
        return this;
    }

    public House setRemark(String notes) {
        this.remark = notes;
        return this;
    }

    public House setDiscontinuedAt(LocalDate discontinuedAt) {
        this.discontinuedAt = discontinuedAt;
        return this;
    }

    public House setHouseStatus(HouseStatus houseStatus) {
        this.houseStatus = houseStatus;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public String getNotes() {
        return remark;
    }

    public LocalDate getDiscontinuedAt() {
        return discontinuedAt;
    }

    public HouseStatus getHouseStatus() {
        return houseStatus;
    }

    public Set<Room> getRooms() {
        return rooms;
    }
}
