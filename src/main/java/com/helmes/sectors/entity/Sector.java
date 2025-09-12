package com.helmes.sectors.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sector")
public class Sector {
    @Id
    private int id;

    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @Transient
    private String indentation;
}