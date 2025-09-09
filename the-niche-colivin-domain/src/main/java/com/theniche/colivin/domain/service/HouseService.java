package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.common.BaseService;
import com.theniche.colivin.domain.entity.House;
import org.springframework.stereotype.Service;

@Service
public class HouseService extends BaseService<House> {

    public HouseService(BaseRepository<House> repository) {
        super(repository);
    }

    @Override
    public House save(House entity) {

        if(entity.getRooms() != null){
            entity.getRooms().forEach(entity::addRoom);
        }
        return super.save(entity);
    }
}
