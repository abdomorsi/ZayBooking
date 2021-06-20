/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class filter {
    
    public ArrayList <HotelView> filteration(ArrayList <HotelView> hotels,String price,String Rate,String Distance ,String breakfast ,String dinner ,String lunch ,String star){
        ArrayList <HotelView> res = new ArrayList<>();
        for(HotelView hotel : hotels){
            Hotel h = hotel.getHotel();
            boolean flag = true;
            if (!(price == null || price.equals("") ||  hotel.getExpectedPrice() <= Integer.parseInt(price))){
                continue;
            }
            if (!(Rate == null || Rate.equals("") || h.getRate() >= Double.parseDouble(Rate))){
                continue;
            }
            if (!(Distance == null || Distance.equals("") || h.getDistance() <= Double.parseDouble(Distance))){
                continue;
            }
            if (!(breakfast == null || breakfast.equals("") || h.getBreakfast() == 1)){
                continue;
            }
            if (!(dinner == null || dinner.equals("") || h.getDinner() == 1)){
                continue;
            }
            if (!(lunch == null || lunch.equals("") || h.getLunch() == 1)){
                continue;
            }
            if (!(star == null || star.equals("")|| h.getStars() >= Integer.parseInt(star) )){
                continue;
            }
            res.add(hotel);
        }
        return res;
    }
    
}
