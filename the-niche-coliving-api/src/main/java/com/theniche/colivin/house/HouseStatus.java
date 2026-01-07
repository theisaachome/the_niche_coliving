package com.theniche.colivin.house;

public enum HouseStatus {
    ACTIVE,
    INACTIVE,
    MAINTENANCE,
    CLOSED
}

// <td><a class="ui teal tag label"
//        [ngClass]="{
//        'green': house.houseStatus === 'ACTIVE',
//        'red': house.houseStatus === 'CLOSED',
//        'yellow': house.houseStatus === 'MAINTENANCE',
//        'grey': house.houseStatus === 'INACTIVE'
//        }"
//        >{{ house.houseStatus }}</a>  </td>

// Capacity - Occupied = Availability
