package com.helmes.sectors.service;

import com.helmes.sectors.entity.Sector;
import com.helmes.sectors.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    public String getHomePage(Model model) {
        List<Sector> hierarchy = sectorRepository.findAllHierarchicallySorted();

        LinkedList<Integer> depthStack = new LinkedList<>();

        for (Sector sector : hierarchy) {
            Integer sectorParentId = sector.getParentId();

            if (!depthStack.contains(sectorParentId)) depthStack.push(sectorParentId);
            else {
                while (!depthStack.getFirst().equals(sectorParentId)) {
                    depthStack.pop();
                    if (depthStack.getFirst() == null) break;
                }
            }

            sector.setIndentation("&nbsp;&nbsp;&nbsp;&nbsp;".repeat(depthStack.size() - 1));
        }

        model.addAttribute("sectors", hierarchy);
        return "index";
    }
}