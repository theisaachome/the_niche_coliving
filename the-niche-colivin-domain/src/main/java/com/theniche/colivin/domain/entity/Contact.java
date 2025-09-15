package com.theniche.colivin.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Contact extends BaseEntity{

    public enum ContactType{
        Primary,Secondary, Emergency
    }
    @Enumerated(EnumType.STRING)
    private ContactType type;
    private String phone;
    private String email;

}
