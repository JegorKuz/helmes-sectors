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

//        ArrayDeque<Integer> depthStack = new ArrayDeque<>();
//
//        for (Sector sector : hierarchy) {
//            Integer sectorParentId = sector.getParentId();
//            log.info("{}", sectorParentId);
//            if (!depthStack.contains(sectorParentId)) depthStack.push(sectorParentId);
//            else {
//                while (!depthStack.getLast().equals(sectorParentId)) {
//                    depthStack.pop();
//                }
//            }
//
//            sector.setIndentation("&nbsp;&nbsp;&nbsp;&nbsp;".repeat(depthStack.size() - 1));
//        }

//        List<Integer> depth = new ArrayList<>();
//
//        Integer prevParentId = null;
//
//        for (Sector sector : hierarchy) {
//            Integer parentId = sector.getParentId();
//            log.info("Parent -> {} | my -> {} | name -> {}", parentId, sector.getId(), sector.getName());
//            if (!depth.contains(parentId)) {
//                depth.add(parentId);
//                if (depth.size() > 1) prevParentId = depth.get(depth.size() - 2);
//            }
//            else if (Objects.equals(parentId, prevParentId) && prevParentId != null) {
//                depth.removeLast();
//                prevParentId = depth.get(depth.size() - 2);
//            }
//            else if (!Objects.equals(parentId, prevParentId)) {
//                int parentIndex = depth.indexOf(parentId);
//                prevParentId = depth.get(parentIndex > 0 ? parentIndex - 1 : parentIndex);
//
//                while (depth.size() - 1 != parentIndex) depth.removeLast();
//            }
//
//            sector.setIndentation("&nbsp;&nbsp;&nbsp;&nbsp;".repeat(depth.size() - 1));
//        }
//        List<Sector> sectors = hierarchy.stream().peek(sector -> sector.setIndentation(getIndentation(sector, hierarchy))).toList();
        model.addAttribute("sectors", hierarchy);
        return "index";
    }

    private String getIndentation(Sector sector, List<Sector> allSectors) {
        int depth = 0;
        Integer parentId = sector.getParentId();
        while (parentId != null) {
            depth++;
            Integer finalParentId = parentId;
            Sector parent = allSectors.stream()
                    .filter(s -> s.getId() == finalParentId)
                    .findFirst()
                    .orElse(null);
            parentId = (parent != null) ? parent.getParentId() : null;
        }
        return "&nbsp;&nbsp;&nbsp;&nbsp;".repeat(depth);
    }
}