package com.helmes.sectors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String status;
    private String reason;
    private String message;
    private String timestamp;
}