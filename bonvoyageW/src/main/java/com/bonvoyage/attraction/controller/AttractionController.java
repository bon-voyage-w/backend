package com.bonvoyage.attraction.controller;

import com.bonvoyage.attraction.entity.AttractionInfoEntity;
import com.bonvoyage.attraction.service.AttractionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("attractions")
public class AttractionController {

    private AttractionService attractionService;

    @GetMapping("/testAttractionList")
    public String list(Model model) {
        List<AttractionInfoEntity> attractions = attractionService.getAttractionList();
        model.addAttribute("attractions", attractions);
        return "testAttractionList";
    }

}
