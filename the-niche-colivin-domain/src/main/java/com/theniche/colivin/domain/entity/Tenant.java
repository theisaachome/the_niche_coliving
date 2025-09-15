package com.theniche.colivin.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenants")
public class Tenant  extends BaseEntity {

    @Column(updatable = false, nullable = false, unique = true)
    private String tenantCode;
    private String fullName;
    @Column(nullable = false)
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;

    @Builder.Default
    @OneToMany(mappedBy = "tenant",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Document>  documents = new HashSet<>();


    @Builder.Default
    @OneToMany(mappedBy = "tenant",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Address> addresses = new HashSet<>();


    public void addTenantDocument(Document document) {
        documents.add(document);
        document.setTenant(this);
    }

    public void removeTenantDocument(Document document) {
        documents.remove(document);
        document.setTenant(null);
    }

    public void removeTenantDocuments(){
        Iterator<Document> iterator = documents.iterator();
        while(iterator.hasNext()){
            iterator.next().setTenant(null);
            iterator.remove();
        }
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setTenant(this);
    }
    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setTenant(null);
    }

    public void removeAddresses(){
        Iterator<Address> iterator = addresses.iterator();
        while(iterator.hasNext()){
            var address = iterator.next();
            address.setTenant(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tenant tenant)) return false;
        return Objects.equals(fullName, tenant.fullName) && Objects.equals(phone, tenant.phone) && Objects.equals(email, tenant.email) && gender == tenant.gender && Objects.equals(dateOfBirth, tenant.dateOfBirth) && Objects.equals(documents, tenant.documents) && Objects.equals(addresses, tenant.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, phone, email, gender, dateOfBirth, documents, addresses);
    }
}
