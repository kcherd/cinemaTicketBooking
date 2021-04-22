package com.test2gis.TicketBooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/hall")
    public String hall(Model model){
        return "hall";
    }
}