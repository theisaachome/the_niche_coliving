package com.theniche.colivin.rest.mapper.address;
import com.theniche.colivin.domain.entity.Address;
import com.theniche.colivin.rest.dto.address.AddressDto;
import com.theniche.colivin.rest.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements BaseMapper<Address, AddressDto, AddressDto> {
    @Override
    public Address requestToEntity(AddressDto requestDto) {
        return Address.builder()
                .addressLine1(requestDto.addressLine1())
                .addressLine2(requestDto.addressLine2())
                .city(requestDto.city())
                .state(requestDto.state())
                .postalCode(requestDto.postalCode())
                .country(requestDto.country())
                .build();
    }

    @Override
    public AddressDto entityToResponse(Address entity) {
        return new AddressDto(
                entity.getId(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getPostalCode(),
                entity.getState(),
                entity.getCountry(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }
}
