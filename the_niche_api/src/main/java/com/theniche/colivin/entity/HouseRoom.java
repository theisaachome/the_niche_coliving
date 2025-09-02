package com.theniche.colivin.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "house_rooms")
public class HouseRoom extends BaseEntity{

    @Column(nullable = false)
    private String roomNumber;
    @Column(nullable = false)
    private Integer capacity;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;
}
