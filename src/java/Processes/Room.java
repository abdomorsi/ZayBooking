/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

/**
 *
 * @author suhai
 */
public class Room {
    private int roomID;
    private String kind;
    private int numOfRooms;
    private double price;
    private int numOfAvailableRoom;
    private String Description;
    private int numOfAdults;
    private int numOfChildren;
    private String facilities;
    private int hotelID;
    
    public Room(int roomID, int hotelID){
        this.roomID = roomID;
        this.hotelID = hotelID;
    }
    public Room(int roomID, String kind, int numOfRooms, double price, int numOfAvailableRoom, String Description, int numOfAdults, int numOfChildren, String facilities, int hotelID) {
        this.roomID = roomID;
        this.kind = kind;
        this.numOfRooms = numOfRooms;
        this.price = price;
        this.numOfAvailableRoom = numOfAvailableRoom;
        this.Description = Description;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.facilities = facilities;
        this.hotelID = hotelID;
    }
    
    public Room(int roomID, String kind, int numOfRooms, double price, String Description, int numOfAdults, int numOfChildren, String facilities, int hotelID) {
        this.roomID = roomID;
        this.kind = kind;
        this.numOfRooms = numOfRooms;
        this.price = price;
        this.numOfAvailableRoom = numOfAvailableRoom;
        this.Description = Description;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.facilities = facilities;
        this.hotelID = hotelID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumOfAvailableRoom() {
        return numOfAvailableRoom;
    }

    public void setNumOfAvailableRoom(int numOfAvailableRoom) {
        this.numOfAvailableRoom = numOfAvailableRoom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }
}
