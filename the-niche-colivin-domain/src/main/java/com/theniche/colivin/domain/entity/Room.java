package com.theniche.colivin.domain.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity{

    @Column(nullable = false)
    private String roomNumber;
    @Column(nullable = false)
    private Integer capacity;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    public Room assignToHouse(House house) {
        this.house = house;
        return this;
    }
}
