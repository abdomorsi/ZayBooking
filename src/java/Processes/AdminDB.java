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
 * @author suhail
 */
public class AdminDB {
     
    public int authenticateAdmin(String email, String password) throws ClassNotFoundException, SQLException{
                
                Connection con = DBmanager.createConnection();
                Statement Stmt = con.createStatement();
                String query = String.format("SELECT * FROM admin where email = '%s' and password = '%s' ;",email,password);
                System.out.println(query);
                ResultSet rs = Stmt.executeQuery(query);
                int result = -1 ; 
                if (rs.next()){
                    result = rs.getInt("adminID");
                }
                rs.close();
                Stmt.close();
                con.close();
                return result;
    }
}
