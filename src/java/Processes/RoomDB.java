/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suhail
 */
public class RoomDB {
    public ArrayList<Room> getArrayList(ResultSet rs) throws SQLException
    {
        ArrayList<Room> rooms = new ArrayList<Room>();
        while(rs.next())
            {
                 Room room = new Room(rs.getInt("roomID"), rs.getString("kind"),rs.getInt("numOfRooms"),
                         rs.getDouble("price"),rs.getInt("numOfAvalibaleRoom"),
                 rs.getString("Describtion"),rs.getInt("numOfAdults"),rs.getInt("numOfChildren"),
                         rs.getString("facilities"),rs.getInt("hotel_hotelID"));
                 rooms.add(room);
            }
        return rooms;
    }
    public ArrayList<Room> search(int hotelID,int numOfChildren, int numOfAdults) throws ClassNotFoundException, SQLException {
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("Select * from rooms where hotel_hotelID = %d and numOfChildren = %d and numOfAdults = %d",
                    hotelID,numOfChildren,numOfAdults);
            ResultSet rs = Stmt.executeQuery(query);
            con.close();
            return getArrayList(rs);
    }
    public ArrayList<Room> getAvailableRooms(ArrayList<Room> rooms,Date checkin, Date checkout) throws ClassNotFoundException, SQLException
    {
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for(Room room: rooms)
        {
            System.out.println("hiiiiiiiiiiii");
            if(new ReservationDB().getNumOfReservations(room.getRoomID(), checkin, checkout) < room.getNumOfRooms())
                availableRooms.add(room);
        }
        return availableRooms;
    }
    public ArrayList<Room> getAvailableRooms(int hotelID,int numOfChildren, int numOfAdults,Date checkin, Date checkout) throws ClassNotFoundException, SQLException {
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("Select * from rooms where hotel_hotelID = %d and numOfChildren = %d and numOfAdults = %d",
                    hotelID,numOfChildren,numOfAdults);
            ResultSet rs = Stmt.executeQuery(query);
            ArrayList<Room> rooms = getAvailableRooms(getArrayList(rs),checkin, checkout);
            con.close();
            return rooms; 
             
             
    }
   
    public boolean updateRoom(Room room) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        String line = String.format("update rooms\n" +
        "set kind = '%s',numOfRooms=%d,price=%f,describtion='%s',numOfChildren=%d,facilities='%s',numOfAdults=%d\n" +
        "where roomID = %d;",room.getKind(),room.getNumOfRooms(),room.getPrice(),room.getDescription(),
        room.getNumOfChildren(),room.getFacilities(),room.getNumOfAdults(),room.getRoomID());
        int row = stmt.executeUpdate(line);
        stmt.close();
        con.close();
        if (row == 0) return false;
        return true;
    }
   
        public boolean addeRoom(Room room) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
            System.out.println("add1");
        String line = String.format("insert into rooms(kind,numOfRooms,price,describtion,numOfChildren,facilities,hotel_hotelID,numOfAdults)\n" +
        " values('%s',%d,%f,'%s',%d,'%s',%d,%d);",room.getKind(),room.getNumOfRooms(),room.getPrice(),room.getDescription(),
        room.getNumOfChildren(),room.getFacilities(),room.getHotelID(),room.getNumOfAdults());
        System.out.println("add2");
        int row = stmt.executeUpdate(line);
        System.out.println("add3");
        stmt.close();
        con.close();
        if (row == 0) return false;
        return true;
        
        }
        
        public Room getRoom(int roomID,int hotelID) throws ClassNotFoundException, SQLException{
            Connection con = DBmanager.createConnection();
            ResultSet rs = null;
            Statement stmt = con.createStatement();
            String line = "select * from rooms where roomID = " + roomID + " and hotel_hotelID = " + hotelID + " ;";
            Room room = null;
            rs = stmt.executeQuery(line);
            if(rs.next()){
                room = new Room(rs.getInt("roomID"),rs.getString("kind"), rs.getInt("numOfRooms"), rs.getInt("price"),rs.getString("describtion"), 
                        rs.getInt("numOfAdults"), rs.getInt("numOfChildren"), rs.getString("facilities"),rs.getInt("hotel_hotelID"));
            }
            rs.close();
            stmt.close();
            con.close();
            return room;
        }
        public ArrayList<Room> getHotelRooms(int hotelID) throws SQLException, ClassNotFoundException
        {
             Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("Select * from rooms where hotel_hotelID = %d;",
                    hotelID);
            ResultSet rs = Stmt.executeQuery(query);
            ArrayList<Room> rooms = getArrayList(rs);
            con.close();
            return rooms;
        }
    
}
