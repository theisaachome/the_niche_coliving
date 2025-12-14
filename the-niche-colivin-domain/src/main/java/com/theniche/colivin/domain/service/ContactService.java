package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.entity.Contact;
import com.theniche.colivin.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactService extends BaseService<Contact> {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        super(contactRepository);
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact update(UUID id, Contact entity) {
        return null;
    }
}
