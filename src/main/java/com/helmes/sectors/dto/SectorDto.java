package com.helmes.sectors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SectorDto {
    private int id;
    private String name;
    private Integer parentId;
    private Integer depth;
}