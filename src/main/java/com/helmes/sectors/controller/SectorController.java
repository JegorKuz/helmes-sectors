package com.helmes.sectors.controller;

import com.helmes.sectors.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorsService;

    @GetMapping("/")
    public String home(Model model) {
        return sectorsService.getHomePage(model);
    }
}