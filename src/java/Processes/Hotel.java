/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

/**
 *
 * @author abdom
 */
public class Hotel {
    private int id;
    private String hotelName;
    private String Description;
    private String src;
    private int numOfRooms;
    private double rate;
    private String town;
    private String location;
    private int stars;
    private int breakfast;
    private int lunch;
    private int dinner;
    private String facilities;
    private String contactInfo;
    private int adminId;
    private double distance;

    public Hotel(){}
    
    public Hotel(int id, String hotelName, String Description, String src, int numOfRooms, double rate, String town, String location, int stars, int breakfast, int lunch, int dinner, String facilities, String contactInfo, int adminId, double distance) {
        this.id = id;
        this.hotelName = hotelName;
        this.Description = Description;
        this.src = src;
        this.numOfRooms = numOfRooms;
        this.rate = rate;
        this.town = town;
        this.location = location;
        this.stars = stars;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.facilities = facilities;
        this.contactInfo = contactInfo;
        this.adminId = adminId;
        this.distance = distance;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
