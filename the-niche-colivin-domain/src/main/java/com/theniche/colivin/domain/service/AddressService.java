package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.entity.Address;
import com.theniche.colivin.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService extends BaseService<Address> {

    private final AddressRepository  addressRepository;
    public AddressService(AddressRepository addressRepository) {
        super(addressRepository);
        this.addressRepository = addressRepository;
    }

    @Override
    public Address update(UUID id, Address entity) {
        return null;
    }
}
