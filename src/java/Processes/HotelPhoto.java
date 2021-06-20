/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

/**
 *
 * @author Lenovo
 */
public class HotelPhoto {
    private int hotelPhotosID;
    private int hotel_hotelID;
    private String source;

    public HotelPhoto(int hotelPhotosID, int hotel_hotelID, String source) {
        this.hotelPhotosID = hotelPhotosID;
        this.hotel_hotelID = hotel_hotelID;
        this.source = source;
    }

    public HotelPhoto() {
    }

    public int getHotelPhotosID() {
        return hotelPhotosID;
    }

    public void setHotelPhotosID(int hotelPhotosID) {
        this.hotelPhotosID = hotelPhotosID;
    }

    public int getHotel_hotelID() {
        return hotel_hotelID;
    }

    public void setHotel_hotelID(int hotel_hotelID) {
        this.hotel_hotelID = hotel_hotelID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    
    


}
