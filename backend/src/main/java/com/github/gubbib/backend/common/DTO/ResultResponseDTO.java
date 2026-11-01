package com.github.gubbib.backend.common.DTO;

public record ResultResponseDTO<T>(
        String message,
        T data
) {
    public ResultResponseDTO(String message){
        this(message, null);
    }
}

