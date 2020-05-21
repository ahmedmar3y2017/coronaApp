package com.example.cornoa.controllers;

import com.example.cornoa.models.Location;
import com.example.cornoa.services.VirusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    VirusData virusData;

    @GetMapping(value = "/")
    public String getRequest(Model model) {
        List<Location> allStats = virusData.getAllStats();

        int totalReportedCases = allStats.stream().mapToInt(value -> value.getTotalCases()).sum();
        int totalPreviousCases = allStats.stream().mapToInt(value -> value.getPreviousCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalPreviousCases", totalPreviousCases);
        return "home";
    }


}
