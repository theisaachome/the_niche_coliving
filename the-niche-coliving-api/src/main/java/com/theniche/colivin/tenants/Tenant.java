package com.theniche.colivin.tenants;

import com.theniche.colivin.common.domain.BaseEntity;
import com.theniche.colivin.common.domain.Gender;
import com.theniche.colivin.common.domain.TenantStatus;
import com.theniche.colivin.util.CodeGenerator;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tenants")
public class Tenant extends BaseEntity {

    @Column(updatable = false, nullable = false, unique = true)
    private String tenantCode;
    private String fullName;
    @Column(nullable = false)
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "tenant_status")
    private TenantStatus tenantStatus = TenantStatus.ACTIVE;


    /*
    @OneToMany(mappedBy = "tenant",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Document>  documents = new HashSet<>();


    @OneToMany(mappedBy = "tenant",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "tenant",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private Set<RoomAssignment> roomAssignments = new HashSet<>();


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

    // Helper methods for managing assignments
    public void assignToRoom(Room room, LocalDate assignmentDate,
                             LocalDate leaseEndDate, AssignmentStatus status) {
        RoomAssignment assignment = new RoomAssignment()
                .setTenant(this)
                .setRoom(room)
                .setAssignmentDate(assignmentDate)
                .setLeaseEndDate(leaseEndDate)
                .setAssignmentStatus(status);

        this.roomAssignments.add(assignment);
        room.getRoomAssignments().add(assignment);
    }

    public void removeRoomAssignment(RoomAssignment assignment) {
        this.roomAssignments.remove(assignment);
        assignment.getRoom().getRoomAssignments().remove(assignment);
        assignment.setTenant(null);
        assignment.setRoom(null);
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

    */
    @PrePersist
    public void prePersist(){
        if(this.tenantCode==null){
            this.tenantCode = CodeGenerator.generateTenantCode();
        }
    }
    public String getTenantCode() {
        return tenantCode;
    }

    public String getFullName() {
        return fullName;
    }

    public Tenant setFullName(String fullName) {
        this.fullName = fullName;
        return  this;
    }

    public String getPhone() {
        return phone;
    }

    public Tenant setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Tenant setEmail(String email) {
        this.email = email;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Tenant setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Tenant setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public TenantStatus getTenantStatus() {
        return tenantStatus;
    }

    public Tenant setTenantStatus(TenantStatus tenantStatus) {
        this.tenantStatus = tenantStatus;
        return this;
    }
}
