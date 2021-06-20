package Processes;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class Reservation {
    private int reservationID;
    private int client_clientID;
    private int rooms_roomID;
    private Date checkIn;
    private Date checkOut;
    private int isCheckedIn;
    private int isCheckedOut;
    private int payment;

    public Reservation(int reservationID, int client_clientID, int rooms_roomID, Date checkIn, Date checkOut, int isCheckedIn, int isCheckedOut, int payment) {
        this.reservationID = reservationID;
        this.client_clientID = client_clientID;
        this.rooms_roomID = rooms_roomID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isCheckedIn = isCheckedIn;
        this.isCheckedOut = isCheckedOut;
        this.payment = payment;
    }

    public Reservation(int reservationID,int client_clientID,int rooms_roomID,
            Date checkIn,Date checkOut,int isChecked) {
        this.reservationID = reservationID;
        this.client_clientID = client_clientID;
        this.rooms_roomID = rooms_roomID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

    }
    public Reservation(){}

    public int getReservationID() {
        return reservationID;
    }

    public int getClient_clientID() {
        return client_clientID;
    }

    public int getRooms_roomID() {
        return rooms_roomID;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getIsChecked() {
        return 0;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setClient_clientID(int client_clientID) {
        this.client_clientID = client_clientID;
    }

    public void setRooms_roomID(int rooms_roomID) {
        this.rooms_roomID = rooms_roomID;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public void setIsChecked(int isChecked) {
    }

    public int getIsCheckedIn() {
        System.out.println(isCheckedIn);
        return isCheckedIn;
    }

    public void setIsCheckedIn(int isCheckedIn) {
        this.isCheckedIn = isCheckedIn;
    }

    public int getIsCheckedOut() {
        return isCheckedOut;
    }

    public void setIsCheckedOut(int isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
    
    
}



