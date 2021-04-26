package com.test2gis.TicketBooking.controllers;

import com.test2gis.TicketBooking.dao.HallDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final HallDAO hallDAO;

    public MainController(HallDAO hallDAO){
        this.hallDAO = hallDAO;
    }

    @GetMapping("/")
    public String films(Model model){
        model.addAttribute("films", hallDAO.films());
        return "films";
    }

    @GetMapping("/film{id}")
    public String cinemas(@PathVariable("id") int id, Model model){
        model.addAttribute("cinemas", hallDAO.cinemas(id));
        model.addAttribute("idFilm", id);
        return "cinemas";
    }

    @GetMapping("/film{id}/cinema{id2}")
    public String halls(@PathVariable("id") int id, @PathVariable("id2") int id2, Model model){
        model.addAttribute("halls", hallDAO.halls(id, id2));
        model.addAttribute("idFilm", id);
        model.addAttribute("idCinema", id2);
        return "halls";
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