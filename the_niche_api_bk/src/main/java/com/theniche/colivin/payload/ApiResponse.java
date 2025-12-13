package com.theniche.colivin.payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiResponse<T extends BaseResponse> (
      String status,
      String message,
      T data
) {}
