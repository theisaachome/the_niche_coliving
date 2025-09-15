package com.theniche.colivin.rest.dto;

import java.util.List;

public record PageApiResponse<T>(
        List<T> content,
        int pageNo,
        int pageSize,
        long totalElement,
        int totalPages,
        boolean last
) {
}
