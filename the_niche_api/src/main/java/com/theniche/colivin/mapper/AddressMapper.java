package com.theniche.colivin.mapper;
import com.theniche.colivin.entity.Address;
import com.theniche.colivin.payload.address.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements IMapper<AddressDto,AddressDto, Address> {
    @Override
    public Address mapToEntity(AddressDto dto) {
        return Address.builder()
                .addressLine1(dto.addressLine1())
                .addressLine2(dto.addressLine2())
                .city(dto.city())
                .country(dto.country())
                .postalCode(dto.postalCode())
                .state(dto.state())
                .country(dto.country())
                .build();
    }

    @Override
    public AddressDto mapToDto(Address entity) {
        return new AddressDto(
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getPostalCode(),
                entity.getCountry(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }
}
