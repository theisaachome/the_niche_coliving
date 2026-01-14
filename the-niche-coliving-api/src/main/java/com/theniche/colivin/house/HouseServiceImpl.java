package com.theniche.colivin.house;
import com.theniche.colivin.common.domain.GenericSpecification;
import com.theniche.colivin.common.domain.SearchCriteria;
import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.common.service.BaseService;
import com.theniche.colivin.house.dto.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class HouseServiceImpl extends BaseService implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    public HouseServiceImpl(HouseRepository houseRepository, HouseMapper houseMapper) {
        this.houseRepository = houseRepository;
        this.houseMapper = houseMapper;
    }

    @Override
    public HouseResponse createHouse(HouseRequest request) {
        var entityHouse = houseMapper.toEntity(request);
        var savedHouse = houseRepository.save(entityHouse);
        return houseMapper.toResponse(savedHouse);
    }

    @Override
    public HouseResponse updateHouse(UUID id, HouseRequest request) {
        var foundEntity = houseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("House","ID",id));
        foundEntity.setName(request.name());
        foundEntity.setLocation(request.location());
        foundEntity.setRemark(request.remark());
        var updatedHouse=  houseRepository.save(foundEntity);
        return houseMapper.toResponse(updatedHouse);
    }

    @Override
    public HouseResponse getHouse(UUID id) {
        var foundEntity = houseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("House","ID",id));
        return houseMapper.toResponse(foundEntity);
    }

    @Override
    public HouseDetailResponse getHouseDetail(UUID id) {
        var foundEntity = houseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("House","ID",id));
        return houseMapper.toDetailResponse(foundEntity);
    }

    @Override
    public void deleteHouse(UUID id) {
        var foundEntity = houseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("House","ID",id));
        foundEntity.setDeleted(true);
        foundEntity.setHouseStatus(HouseStatus.INACTIVE);
        houseRepository.save(foundEntity);
    }

    @Override
    public String archiveHouse(UUID id) {
        var existingEntity = houseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("House","ID",id));
        existingEntity.setHouseStatus(HouseStatus.INACTIVE);
        houseRepository.save(existingEntity);
        return String.format("Successfully archived house with ID: %s", id);
    }

    @Override
    public String unarchiveHouse(UUID id) {
        var existingEntity = houseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("House","ID",id));
        existingEntity.setHouseStatus(HouseStatus.ACTIVE);
        houseRepository.save(existingEntity);
        return String.format("Successfully unarchived house with ID: %s", id);
    }

    @Override
    public List<HouseResponse> getHouses() {
        return houseRepository.findAll().stream()
                .map(houseMapper::toResponse).toList();
    }

    @Override
    public HouseResponse searchHouse(HouseSearchFilters filters) {
        GenericSpecification<House> specification = new GenericSpecification<>();

        addIfHasText(specification,"houseCode",filters.getHouseCode(), SearchCriteria.Operation.EQUAL);
        addIfHasText(specification,"name",filters.getHouseCode(), SearchCriteria.Operation.EQUAL);
        log.info("Search filters count: {}", specification.getCriteriaCount());
       var result=  houseRepository.findOne( specification.build())
               .orElseThrow(()->new EntityNotFoundException("No House Found"));
        return houseMapper.toResponse(result);
    }

    @Override
    public List<HouseOverviewResponse> getHouseOverview(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        var resultPage= houseRepository.findHouseOverview(pageable);
       var list= resultPage.getContent();
       return  list;
    }

    private void addIfHasText( GenericSpecification<House> spec,
                               String field,
                               String value,
                               SearchCriteria.Operation operation){
        if(StringUtils.hasText(value)){
            spec.add(new SearchCriteria(field,value,operation));
        }
    }
}
