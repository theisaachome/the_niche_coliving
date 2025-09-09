package com.theniche.colivin.domain.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="houses",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_HOUSE_NAME", columnNames = "name")
        }
)
public class House extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String name;
    @Column(nullable = false,length = 225)
    private String address;
    private String description;
    private String notes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "house", orphanRemoval = true)
    @Builder.Default
    private Set<Room> rooms =new HashSet<>();

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
}
