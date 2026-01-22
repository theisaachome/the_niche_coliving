package com.theniche.colivin.house;

import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.room.RoomStatus;
import com.theniche.colivin.room.dto.RoomCreatedEvent;
import com.theniche.colivin.room.dto.RoomStatusChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class HouseRoomCounterHelper {
    private final static Logger logger = LoggerFactory.getLogger(HouseRoomCounterHelper.class);
    private final HouseRepository houseRepository;

    public HouseRoomCounterHelper(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @TransactionalEventListener(phase =  TransactionPhase.BEFORE_COMMIT)
    public void onRoomCreated(RoomCreatedEvent event) {
        logger.info("Room Created Event: {}", event);
        var house = houseRepository.findById(event.houseId())
                .orElseThrow(() -> new ResourceNotFoundException("House", "id", event.houseId()));
        house.setTotalRooms(house.getTotalRooms() + 1);

        if(event.roomStatus() == RoomStatus.AVAILABLE) {
            house.setAvailableRooms(house.getAvailableRooms() + 1);
        }
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onRoomStatusUpdate(RoomStatusChangedEvent event){
        logger.info("Room Status Updated Event: {}", event);
        var house = houseRepository.findById(event.houseId())
                .orElseThrow(() -> new ResourceNotFoundException("House", "id", event.houseId()));

        if (RoomStatus.AVAILABLE.equals(event.oldStatus())) {
            house.incrementAvailableRooms();
        }
        if (event.oldStatus() !=null && event.newStatus().isAvailable()) {
            house.decreaseAvailableRooms();
        }
    }
}
