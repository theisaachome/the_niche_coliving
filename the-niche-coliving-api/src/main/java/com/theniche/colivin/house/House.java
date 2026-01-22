package com.theniche.colivin.house;
import com.theniche.colivin.common.domain.BaseEntity;
import com.theniche.colivin.room.Room;
import com.theniche.colivin.util.CodeGenerator;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "house_status")
    private HouseStatus houseStatus =HouseStatus.ACTIVE;
    @Column(name = "location",nullable = false)
    private String location;
    @Column(name = "remark")
    private String remark;
    @Column(nullable = false)
    private long totalRooms = 0;
    @Column(nullable = false)
    private long availableRooms = 0;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "house",orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

    public Set<Room> getRooms() {
        return rooms;
    }

    public House(){}

    public void addRoom(Room room){
        this.rooms.add(room);
        room.setHouse(this);
    }
    public void removeRoom(Room room){
        rooms.remove(room);
        room.setHouse(null);
    }

    public void incrementAvailableRooms (){
        if(this.availableRooms < totalRooms){
            this.availableRooms ++;
        }
    }
    public void decreaseAvailableRooms (){
        if (availableRooms > 0) {
            availableRooms --;
        }
    }

    public long getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(long totalRooms) {
        this.totalRooms = totalRooms;
    }

    public long getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(long availableRooms) {
        this.availableRooms = availableRooms;
    }

    public void removeRooms(){
        Iterator<Room> iterator = this.rooms.iterator();
        while (iterator.hasNext()) {
            var room = iterator.next();
            room.setHouse(null);
            iterator.remove();
        }
    }

    public String getName() {
        return name;
    }

    public House setName(String name) {
        this.name = name;
        return this;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public House setHouseCode(String houseCode) {
        this.houseCode = houseCode;
        return this;
    }

    public HouseStatus getHouseStatus() {
        return houseStatus;
    }

    public House setHouseStatus(HouseStatus houseStatus) {
        this.houseStatus = houseStatus;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public House setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public House setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @PrePersist
    public void prePersist() {
        if(this.houseCode == null) {
            this.houseCode = CodeGenerator.generateHouseCode();
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
}
