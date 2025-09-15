package com.theniche.colivin.domain.service;

import org.springframework.stereotype.Service;

@Service
public class TenantManagementService {

    // assign tenant to room
    // unassign tenant from room

    // query tenant monthly payment status

    // handle security deposit
}


/*
@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantActivityController {

    private final TenantActivityService activityService;

    @PostMapping("/{tenantId}/check-in")
    public ResponseEntity<TenantActivityResponse> checkIn(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(activityService.checkIn(tenantId));
    }

    @PostMapping("/{tenantId}/check-out")
    public ResponseEntity<TenantActivityResponse> checkOut(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(activityService.checkOut(tenantId));
    }

    @GetMapping("/{tenantId}/payments/status")
    public ResponseEntity<PaymentStatusResponse> getPaymentStatus(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(activityService.getPaymentStatus(tenantId));
    }

    @GetMapping("/{tenantId}/payments/history")
    public ResponseEntity<List<PaymentHistoryResponse>> getPaymentHistory(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(activityService.getPaymentHistory(tenantId));
    }

    @GetMapping("/{tenantId}/activities")
    public ResponseEntity<List<TenantActivityResponse>> getTenantActivities(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(activityService.getAllActivities(tenantId));
    }
}
POST /api/v1/tenants/{id}/assign-room

POST /api/v1/tenants/{id}/terminate

PUT /api/v1/tenants/{id}/extend-contract
 */
