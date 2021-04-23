package com.test2gis.TicketBooking.dao;

import com.test2gis.TicketBooking.models.Seat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HallDAO {

    private ArrayList<Seat> seats;

    {
        seats = new ArrayList<>();
        seats.add(new Seat(1, 1,1, true));
        seats.add(new Seat(2, 1,2, true));
        seats.add(new Seat(3, 2,1, true));
        seats.add(new Seat(4, 2,2, true));
    }

    //Список мест в зале
    public List<Seat> seats(){
        return seats;
    }

    //Бронирование мест в зале
    public boolean reservation(int [] seatsId){
        for (int id: seatsId) {
            for (Seat seat : seats) {
                if (seat.getId() == id) {
                    if (seat.getStatus()) {
                        seat.setStatus(false);
                    }
                }
            }
        }
        return true;
    }
}
