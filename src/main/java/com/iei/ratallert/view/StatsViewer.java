package com.iei.ratallert.view;

import com.iei.ratallert.database.controllers.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsViewer {

    @Autowired
    StatsService statsService;

    @GetMapping("/v1/stats")
    public String greeting(Model model) {
        String hourlyStats = statsService.getAll().toString();
        model.addAttribute("hourlyStats", hourlyStats);
        return "statsPage";
    }
}
