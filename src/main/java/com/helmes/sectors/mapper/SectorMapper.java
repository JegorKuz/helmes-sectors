package com.helmes.sectors.mapper;

import com.helmes.sectors.dto.SectorDto;
import com.helmes.sectors.entity.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectorMapper {

    @Mapping(target = "indentation", source = "dto", qualifiedByName = "mapIndentation")
    Sector dtoToEntity(SectorDto dto);

    List<Sector> dtoListToEntityList(List<SectorDto> dtos);

    @Named("mapIndentation")
    default String mapIndentation(SectorDto dto) {
        return "&nbsp;&nbsp;&nbsp;&nbsp;".repeat(dto.getDepth());
    }
}