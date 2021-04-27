package com.test2gis.TicketBooking.dao;

import com.test2gis.TicketBooking.models.Cinema;
import com.test2gis.TicketBooking.models.Film;
import com.test2gis.TicketBooking.models.Seat;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class HallDAO {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/cinemas_db";
    private static final String USER = "postgres";
    private static final String PASS = "";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Film> films(){
        List<Film> films = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            String query = "select * from films";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Film film = new Film();

                film.setId(resultSet.getInt("id"));
                film.setName(resultSet.getString("name"));
                film.setDuration(resultSet.getString("duration"));

                films.add(film);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return films;
    }

    public String film(int id){
        StringBuilder name = new StringBuilder();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select name from films where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                name.append(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return name.toString();
    }

    public List<Cinema> cinemas(int id){
        List<Cinema> cinemas = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select cinemas.id, cinemas.name, cinemas.address " +
                    "from cinemas join cinema_hall on cinemas.id = cinema_hall.id_c join movie_hall " +
                    "on movie_hall.id_hall = cinema_hall.id join films on films.id = movie_hall.id_film where films.id = ?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Cinema cinema = new Cinema();

                cinema.setId(resultSet.getInt("id"));
                cinema.setName(resultSet.getString("name"));
                cinema.setAddress(resultSet.getString("address"));

                cinemas.add(cinema);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return cinemas;
    }

    public String cinema(int id){
        StringBuilder name = new StringBuilder();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select name from cinemas where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                name.append(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return name.toString();
    }

    public List<Integer> halls(int idFilm, int idCinema){
        List<Integer> halls = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select cinema_hall.id from cinema_hall " +
                    "join movie_hall on movie_hall.id_hall = cinema_hall.id where cinema_hall.id_c = ? and movie_hall.id_film = ?");
            preparedStatement.setInt(1, idCinema);
            preparedStatement.setInt(2, idFilm);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                halls.add(resultSet.getInt("id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return halls;
    }

    //Список мест в зале
    public List<Seat> seats(int idHall){
        List<Seat> seatList = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id_chair, row_num, col_num, status from halls where id_hall = ? order by id");
            preparedStatement.setInt(1, idHall);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Seat seat = new Seat();

                seat.setId(resultSet.getInt("id_chair"));
                seat.setRowNum(resultSet.getInt("row_num"));
                seat.setChairNum(resultSet.getInt("col_num"));
                seat.setStatus(resultSet.getBoolean("status"));

                seatList.add(seat);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return seatList;
    }

    //Бронирование мест в зале
    public boolean reservation(int [] seatsId, int idHall){
        int result = 0;
        try {
            for (int i = 0; i < seatsId.length; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement("update halls set status=false where id_hall = ? and id_chair=?");
                preparedStatement.setInt(1, idHall);
                preparedStatement.setInt(2, seatsId[i]);

                result = preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result > 0;
    }
}
