package com.theniche.colivin.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenants")
public class Tenant  extends BaseEntity {
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "tenant",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<TenantDocument>  documents;


    public void addTenantDocument(TenantDocument tenantDocument) {
        documents.add(tenantDocument);
        tenantDocument.setTenant(this);
    }

    public void removeTenantDocument(TenantDocument tenantDocument) {
        documents.remove(tenantDocument);
        tenantDocument.setTenant(null);
    }

    public void removeTenantDocuments(){
        Iterator<TenantDocument> iterator = documents.iterator();
        while(iterator.hasNext()){
            iterator.next().setTenant(null);
            iterator.remove();
        }
    }
}
