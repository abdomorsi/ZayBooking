/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author suhai
 */
public class HotelDB {
    public Hotel getHotel(ResultSet rs) throws SQLException
    {
        Hotel hotel = new Hotel(rs.getInt("hotelID"),rs.getString("hotelName"),
            rs.getString("describtion"), rs.getString("photo"), rs.getInt("numOfRoom"), rs.getDouble("rate"),
            rs.getString("town"), rs.getString("location"), rs.getInt("stars"), rs.getInt("breakfast"), rs.getInt("lunch"),
                    rs.getInt("dinner"),rs.getString("facilities"), rs.getString("contectInformation"),
                    rs.getInt("admin_adminID"), rs.getDouble("distance"));
        return hotel;
    }
    ArrayList<HotelView> setHotelViews(ResultSet rs, int numOfAdults,int numOfChildren,Date checkIn, Date checkout) throws SQLException, ClassNotFoundException
    {
        ArrayList<HotelView> hotelViews = new ArrayList<HotelView>();
        while(rs.next())
        {
            Hotel hotel = getHotel(rs);
            System.out.println(hotel.getHotelName());
            Double expectedPrice = 0.0;
            boolean isAvailable = true;
            ArrayList<Room> rooms = new RoomDB().getAvailableRooms(hotel.getId(), numOfChildren, numOfAdults, checkIn, checkout);
            if(rooms.size() == 0)
                isAvailable = false;
            else
                expectedPrice = rooms.get(0).getPrice();
            System.out.println(expectedPrice+" "+hotel+" "+isAvailable);
            HotelView hotelView = new HotelView(hotel,expectedPrice,isAvailable);
            hotelViews.add(hotelView);
        }
        return hotelViews;
    }
    public ArrayList<HotelView> search(String town, Date checkIn, Date checkout,int numOfAdults,int numOfChildren) throws ClassNotFoundException, SQLException{
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("select * from hotel where town = '%s';",town.toLowerCase());
            System.out.println(query);
            ResultSet rs = Stmt.executeQuery(query);
            ArrayList<HotelView> hotelViews = setHotelViews(rs,numOfAdults,numOfChildren,checkIn,checkout);
            rs.close();
            Stmt.close();
            con.close();
            return hotelViews;
    }
    public Hotel getHotel(int hotelID) throws ClassNotFoundException, SQLException
    {
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("select * from hotel where hotelID = %d;",hotelID);
            ResultSet rs = Stmt.executeQuery(query);           
            rs.next();
            Hotel hotel = getHotel(rs);
            rs.close();
            Stmt.close();
            con.close();
            return hotel;
    }
    public void addAReview(int hotelID,int clientID,double rating,String comment) throws ClassNotFoundException, SQLException
    {
            System.out.println("hi");
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("insert into hotelreviews (client_clientID,hotel_hotelID,rate,comment) values (%d,%d,%f,'%s');",clientID,hotelID,rating,comment);
            System.out.println(query);
            int row = Stmt.executeUpdate(query);
            
            Hotel hotel = getHotel(hotelID);
            query = String.format("select count(*) from hotelreviews where hotel_hotelID = %d",hotelID);
            System.out.println(query);
            ResultSet rs = Stmt.executeQuery(query);
            System.out.println("hi2222");
            rs.next();
            double newRate = (hotel.getRate() * (rs.getDouble("count(*)")-1) + rating) / (rs.getDouble("count(*)"));
            hotel.setRate(newRate);
            System.out.println(newRate);
            updateHotel(hotel);
            System.out.println("final");
            rs.close();
            Stmt.close();
            con.close();
    }
    public Boolean updateHotel(Hotel hotel) throws ClassNotFoundException, SQLException
    {
            
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("UPDATE hotel\n" +
            "Set hotelName = '%s',describtion = '%s',photo='%s',numOfRoom=%d,rate=%f,town='%s',location='%s',stars=%d,\n" +
            "breakfast=%d,lunch=%d,dinner=%d,facilities='%s',contectInformation='%s',admin_adminID=%d, distance = %f\n" +
            "WHERE hotelID = %d;",hotel.getHotelName(),hotel.getDescription(),hotel.getSrc(),hotel.getNumOfRooms(),hotel.getRate(),
            hotel.getTown(),hotel.getLocation(),hotel.getStars(),hotel.getBreakfast(),hotel.getLunch(),hotel.getDinner(),hotel.getFacilities()
            ,hotel.getContactInfo(),hotel.getAdminId(),hotel.getDistance(),hotel.getId());
            int res = Stmt.executeUpdate(query);
            Stmt.close();
            con.close();
            if(res > 0) return true;
            else return false;
            
    }
    public void addPhoto(int hotelID, String src) throws ClassNotFoundException, SQLException{
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("insert into hotelphotos (hotel_hotelID,source) value (%d,'%s');",hotelID,src);
            Stmt.executeUpdate(query);
            Stmt.close();
            con.close();
    }
    public ArrayList<Review> getReviews(int hotelID) throws ClassNotFoundException, SQLException
    {
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("select * from hotelreviews where hotel_hotelID = %d;",hotelID);
            System.out.println(query);
            ResultSet rs = Stmt.executeQuery(query);   
            ArrayList<Review> reviews = new ArrayList<Review>();
            while(rs.next())
            {
                Review review = new Review(rs.getInt("hotelReviewsID"), rs.getInt("client_clientID"),
                        rs.getInt("hotel_hotelID"), rs.getDouble("rate"), rs.getString("comment"));
                reviews.add(review);
            }
            rs.close();
            Stmt.close();
            con.close();
            return reviews;
    }
    
    public int getHotelAdmin(int adminID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        Statement Stmt = con.createStatement();
        String query = String.format("select * from hotel where admin_adminID = %d;",adminID);///select * from hotel where admin_adminID = 1 ;
        ResultSet rs = Stmt.executeQuery(query);   
        int res = -1;
        if(rs.next())
        {
            res = rs.getInt("hotelID");
        }
        rs.close();
        Stmt.close();
        con.close();
        return res;
    }
    
    public boolean deletePhoto(int hotelID, String source) throws ClassNotFoundException, SQLException{
            Connection con = DBmanager.createConnection();
            Statement Stmt = con.createStatement();
            String query = String.format("select * from hotelphotos where source = '%s' and hotel_hotelID = %d;",source,hotelID);
            ResultSet rs = Stmt.executeQuery(query);
            if(!rs.next()) return false;
            rs.close();
            query = String.format("delete from hotelphotos where source = '%s';",source);
            int res = Stmt.executeUpdate(query);
            rs.close();
            Stmt.close();
            con.close();
            if (res > 0) return true;
            else return false;
       
    }
    
}
