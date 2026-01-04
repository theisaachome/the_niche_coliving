package com.theniche.colivin.room;

import com.theniche.colivin.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

    @Column(nullable = false)
    private String roomNumber;
    @Column(nullable = false,unique = true,updatable = false,name = "room_code")
    private String roomCode;

    @Column(nullable = false)
    private Integer capacity;

}
