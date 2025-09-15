package com.helmes.sectors.service;

import com.helmes.sectors.dto.SectorDto;
import com.helmes.sectors.entity.Sector;
import com.helmes.sectors.mapper.SectorMapper;
import com.helmes.sectors.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public String getHomePage(Model model) {
        List<SectorDto> hierarchy = sectorRepository.findAllHierarchicallySortedWithDepth();
        List<Sector> sectors = sectorMapper.dtoListToEntityList(hierarchy);
        model.addAttribute("sectors", sectors);

        return "index";
    }
}