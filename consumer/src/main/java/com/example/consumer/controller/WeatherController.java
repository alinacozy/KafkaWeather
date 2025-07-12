package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.consumer.model.WeatherStatsDTO;
import com.example.consumer.service.WeatherStatsService;


@Controller
public class WeatherController {

    @Autowired
    private WeatherStatsService statsService;
    
    @GetMapping("/stats")
    public String getStats(Model model) {
        WeatherStatsDTO stats=statsService.getStats();
        model.addAttribute("stats", stats);
        return "stats";
    }
    
}
