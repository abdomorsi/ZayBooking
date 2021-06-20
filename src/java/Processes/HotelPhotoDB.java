/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class HotelPhotoDB {
    
    public ArrayList <String> getHotelPhoto(int hotelID) throws ClassNotFoundException, SQLException{
        Connection con = DBmanager.createConnection();
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        ArrayList <String> res = new ArrayList<>();
        String line = "select * from hotelphotos where hotel_hotelID = " + hotelID + " ;";
        rs = stmt.executeQuery(line);
        while (rs.next()){
            res.add(rs.getString("source"));
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }
    
}
