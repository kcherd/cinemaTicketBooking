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
        model.addAttribute("filmName", hallDAO.film(id));
        return "cinemas";
    }

    @GetMapping("/film{id}/cinema{id2}")
    public String halls(@PathVariable("id") int id, @PathVariable("id2") int id2, Model model){
        model.addAttribute("halls", hallDAO.halls(id, id2));
        model.addAttribute("idFilm", id);
        model.addAttribute("idCinema", id2);
        model.addAttribute("filmName", hallDAO.film(id));
        model.addAttribute("cinemaName", hallDAO.cinema(id2));
        return "halls";
    }

    @GetMapping("/film{id}/cinema{id2}/hall{id3}")
    public String hall(@PathVariable("id") int id, @PathVariable("id2") int id2, @PathVariable("id3") int id3, Model model){
        model.addAttribute("seats", hallDAO.seats(id3));
        model.addAttribute("idFilm", id);
        model.addAttribute("idCinema", id2);
        model.addAttribute("idHall", id3);
        return "hall";
    }

    @PostMapping("/film{id}/cinema{id2}/hall{id3}")
    public String reserve(@PathVariable("id") int id, @PathVariable("id2") int id2, @PathVariable("id3") int id3, @RequestParam int [] chair){
        hallDAO.reservation(chair, id3);
        return "redirect:/film{id}/cinema{id2}/hall{id3}";
    }

//    @GetMapping("/hall")
//    public String hall(Model model){
//        model.addAttribute("curHall", hallDAO.seats());
//        return "hall";
//    }
//
//    @PostMapping("/hall")
//    public String reserve(@RequestParam int [] chair){
//        hallDAO.reservation(chair);
//        return "redirect:/hall";
//    }
}