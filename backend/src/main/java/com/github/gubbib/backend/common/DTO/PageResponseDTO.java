package com.github.gubbib.backend.common.DTO;

import java.util.List;

public record PageResponseDTO<T>(
        long totalCount,
        int page,
        int perPage,
        List<T> list
) {
}
