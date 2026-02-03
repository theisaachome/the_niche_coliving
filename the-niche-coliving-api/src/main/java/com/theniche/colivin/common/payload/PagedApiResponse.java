package com.theniche.colivin.common.payload;

import java.util.List;

public record PagedApiResponse<T>(
        List<T> content,
        int pageNo,
        int pageSize,
        long totalElement,
        int totalPage,
        boolean last
) {
}
