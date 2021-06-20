/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


/**
 *
 * @author abdom
 */
public class Checking {
    
    public static boolean newUser( User user ) throws ClassNotFoundException, SQLException{
        boolean status = false;
        Map <String,String> userInfo = user.getUserInfo();
        ArrayList<String> values =  new ArrayList<>();
        values.add("*");
        Map<String,String> colsCondition = new HashMap<>();
        colsCondition.put("email", userInfo.get("email"));
        ResultSet rs = DBmanager.select("client", values, colsCondition);
        status = !rs.next();
        //System.out.println(n + " <<<");
        return status;
    }
    
    
}
