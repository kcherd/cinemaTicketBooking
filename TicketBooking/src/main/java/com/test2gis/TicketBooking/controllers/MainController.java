package com.test2gis.TicketBooking.controllers;

import com.test2gis.TicketBooking.dao.HallDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final HallDAO hallDAO;

    public MainController(HallDAO hallDAO){
        this.hallDAO = hallDAO;
    }

    @GetMapping("/hall")
    public String hall(Model model){
        model.addAttribute("curHall", hallDAO.seats());
        return "hall";
    }

    @PostMapping("/hall")
    public String reserve(@RequestParam int [] chair){
        hallDAO.reservation(chair);
        return "redirect:/hall";
    }
}