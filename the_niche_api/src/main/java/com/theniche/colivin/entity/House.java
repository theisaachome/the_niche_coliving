package com.theniche.colivin.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="houses")
public class House extends BaseEntity {

    @Column(unique = true,nullable = false)
    private String name;
    @Column(nullable = false,length = 225)
    private String address;
    private String description;
    private String notes;
    @OneToMany(mappedBy = "house")
    private Set<HouseRoom> rooms;
}
