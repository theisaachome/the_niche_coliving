package com.theniche.colivin.common.payload;

import java.util.List;

public record PageApiResponse<T>(
        List<T> content,
        int pageNo,
        int pageSize,
        long totalElement,
        int totalPage,
        boolean last
) {
}
