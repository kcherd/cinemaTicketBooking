package com.test2gis.TicketBooking.dao;

import com.test2gis.TicketBooking.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class HallDAO {

    //Список мест в зале
    public List<Seat> seats(){
        return new ArrayList<>();
    }

    //Бронирование мест в зале
    public boolean reservation(ArrayList<Seat> seats){
        return true;
    }
}
