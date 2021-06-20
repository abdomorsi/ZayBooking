package Processes;


import Processes.DBmanager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class ReservationDB {
    public int getNumOfReservations(int roomID, Date checkIn, Date checkOut) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        int res = -1;
        String line = "select COUNT(*) from reservation where rooms_roomID = " + roomID + " and ((checkIn BETWEEN '" + checkIn + "' AND '" + checkOut + "') or \n" +
                      "	(checkOut BETWEEN '" + checkIn + "' AND '" + checkOut + "')) ;";
        System.out.println(line);
        rs = stmt.executeQuery(line);
        if (rs.next()){
            res = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }
    
   public boolean cancelReservation(int reservationID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        String line = "delete from reservation where reservationID = " + reservationID + ";";
        int row = stmt.executeUpdate(line);
        stmt.close();
        con.close();
        if (row == 0) return false;
        return true;
    }
    
    public Integer makeResvation(int roomID, Date checkIn, Date checkOut, int clientID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;
        String line = "insert into reservation (client_clientID,rooms_roomID,checkIn,checkOut,isChecked)  values (" + clientID + "," + roomID + ",'" +
                        checkIn + "','" + checkOut + "',0);";
        System.out.println(line);
        Integer res = null;
        int row = stmt.executeUpdate(line);
        if (row > 0){
            line = String.format("select * from reservation where client_clientID = %d and rooms_roomID = %d and checkIn = '%s' and checkOut = '%s';",clientID,roomID,checkIn,checkOut);
            rs = stmt.executeQuery(line);
            while(rs.next()){
                res = rs.getInt("reservationID");
            }
        }
        stmt.close();
        con.close();
        return res;
    }
    
    public ArrayList<Reservation> viewCurrentReservation(int hotelID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        String line = "select * from rooms join reservation on\n" +
                      "	rooms.roomID = reservation.rooms_roomID and rooms.hotel_hotelID = "+ hotelID + " and reservation.isChecked = 0 ;";
        ArrayList<Reservation> results = new ArrayList<>();
        rs = stmt.executeQuery(line);
        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setReservationID(rs.getInt("reservationID"));
            reservation.setCheckIn(rs.getDate("checkIn"));
            reservation.setCheckOut(rs.getDate("checkOut"));
            reservation.setClient_clientID(rs.getInt("client_clientID"));
            reservation.setIsChecked(rs.getInt("isChecked"));
            reservation.setRooms_roomID(rs.getInt("rooms_roomID"));
            reservation.setIsCheckedIn(rs.getInt("isCheckedIn"));
            reservation.setIsCheckedOut(rs.getInt("isCheckedOut"));
            reservation.setPayment(rs.getInt("Payment"));
            results.add(reservation);
        }
        rs.close();
        stmt.close();
        con.close();
        return results;
    }
    public ArrayList<Reservation> viewReservationUsingData(int hotelID,Date from,Date to) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        String line = "select * from rooms join reservation  "
                        + "on rooms.roomID = reservation.rooms_roomID and hotel_hotelID = " + hotelID + " where reservation.isChecked = 0 and \n" +
"                       (checkIn BETWEEN '" + from + "' AND '"+ to + "') or (checkOut BETWEEN '" + from + "' AND '" + to + "');";
        ArrayList<Reservation> results = new ArrayList<>();
        rs = stmt.executeQuery(line);
        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setReservationID(rs.getInt("reservationID"));
            reservation.setCheckIn(rs.getDate("checkIn"));
            reservation.setCheckOut(rs.getDate("checkOut"));
            reservation.setClient_clientID(rs.getInt("client_clientID"));
            reservation.setIsChecked(rs.getInt("isChecked"));
            reservation.setIsCheckedIn(rs.getInt("isCheckedIn"));
            reservation.setIsCheckedOut(rs.getInt("isCheckedOut"));
             reservation.setPayment(rs.getInt("Payment"));
            reservation.setRooms_roomID(rs.getInt("rooms_roomID"));
            results.add(reservation);
        }
        rs.close();
        stmt.close();
        con.close();
        return results;
    }
    public Reservation getReservationByID(int id) throws SQLException, ClassNotFoundException{
        Connection con = DBmanager.createConnection();
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        String line = String.format("select * from reservation where reservationID = %d;",id);
        System.out.println(line);
        rs = stmt.executeQuery(line);
        Reservation reservation = null;
        if (rs.next()){
            reservation = new Reservation();
            reservation.setReservationID(rs.getInt("reservationID"));
            reservation.setClient_clientID(rs.getInt("client_clientID"));
            reservation.setRooms_roomID(rs.getInt("rooms_roomID"));
            reservation.setCheckIn(rs.getDate("checkIn"));
            reservation.setCheckOut(rs.getDate("checkOut"));
               reservation.setIsCheckedIn(rs.getInt("isCheckedIn"));
            reservation.setIsCheckedOut(rs.getInt("isCheckedOut"));
            reservation.setPayment(rs.getInt("Payment"));
        }
        System.out.println("heeere");
        rs.close();
        stmt.close();
        con.close();
        return reservation;
    }
    public boolean updateReservation(Reservation reservation) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        String line = "UPDATE reservation SET \n" +
                        "    client_clientID = " + reservation.getClient_clientID() + ",\n" +
                        "    rooms_roomID = " + reservation.getRooms_roomID() + ",\n" +
                        "    checkIn = '" + reservation.getCheckIn() + "',\n" +
                        "    checkOut = '" + reservation.getCheckOut() + "',\n" +
                        "    isChecked = " + reservation.getIsChecked() + ",\n" +
                        "    isCheckedIn = " + reservation.getIsCheckedIn()+",\n"+
                        "    isCheckedOut = " + reservation.getIsCheckedOut()+",\n"+
                        "    Payment = " + reservation.getPayment() +"\n"+
                        "    WHERE\n" +
                        "    reservationID = " + reservation.getReservationID() + ";";
        System.out.println(line);
        int row = stmt.executeUpdate(line);
        stmt.close();
           System.out.println(line);
     con.close();
        if (row == 0) return false;
        return true;
    }
    public boolean checkForAdmin(int hotelID , int reservationID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;
        String line = "select reservationID,client_clientID,rooms_roomID,checkIn,checkOut,isChecked from rooms join reservation \n" +
                        "on rooms.roomID = reservation.rooms_roomID and rooms.hotel_hotelID = " + hotelID + " and reservationID = " + reservationID + ";";
        rs = stmt.executeQuery(line);
        boolean flage = rs.next();
        rs.close();
        stmt.close();
        con.close();
        return flage;
    }
    public String getAdminEmail(int reservationID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;
        String line = "select email from reservation join rooms join hotel join admin on reservation.rooms_roomID = rooms.roomID and \n" +
                      "	rooms.hotel_hotelID = hotel.hotelID and hotel.admin_adminID = admin.adminID where reservationID = " + reservationID + ";";
        rs = stmt.executeQuery(line);
        String res = null;
        if(rs.next()){
            res = rs.getString("email");
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }
    public boolean checkForClient(int clientID , int reservationID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;
        String line = "select * from reservation where client_clientID = " + clientID + ";";
         System.out.println(line);
        rs = stmt.executeQuery(line);
        boolean flage = rs.next();
        rs.close();
        stmt.close();
        con.close();
        return flage;
    }
}   
