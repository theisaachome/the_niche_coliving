package com.theniche.colivin.room;

public enum RoomStatus {
    AVAILABLE,OCCUPIED;

    public boolean isAvailable() {
        return this == AVAILABLE;
    }
}
