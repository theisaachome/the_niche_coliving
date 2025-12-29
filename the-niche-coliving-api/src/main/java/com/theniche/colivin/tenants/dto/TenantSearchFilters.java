package com.theniche.colivin.tenants.dto;

public class TenantSearchFilters {
    private String email;
    private String tenantCode;
    private String phone;
    private String fullName;

    public TenantSearchFilters() {}
    public TenantSearchFilters(String email, String tenantCode, String phone, String fullName) {
        this.email = email;
        this.tenantCode = tenantCode;
        this.phone = phone;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
