package com.helmes.sectors.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "User must insert name")
    private String name;

    @NotNull(message = "User must select sector")
    private Integer sectorId;

    @NotNull(message = "User must agree to terms")
    @AssertTrue(message = "User must agree to terms")
    private Boolean terms;
}