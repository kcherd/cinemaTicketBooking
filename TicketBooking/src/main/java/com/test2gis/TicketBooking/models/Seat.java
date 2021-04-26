package com.test2gis.TicketBooking.models;

public class Seat {
    private int id;
    private int rowNum;
    private int chairNum;
    private boolean status;

    public Seat(){}

    public Seat(int id, int rowNum, int chairNum, boolean status) {
        this.id = id;
        this.rowNum = rowNum;
        this.chairNum = chairNum;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getChairNum() {
        return chairNum;
    }

    public void setChairNum(int chairNum) {
        this.chairNum = chairNum;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
