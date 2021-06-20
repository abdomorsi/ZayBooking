package Processes;

import Processes.Client;
import Processes.DBmanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class ClientDB {

    public Client getClient(int clientID) throws ClassNotFoundException, SQLException {
        String line = "";
        Client client = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = DBmanager.createConnection();
        stmt = con.createStatement();
        line = "select * from client where clientID = " + clientID + " ;";
        rs = stmt.executeQuery(line);
        if (rs.next()) {
            client = new Client();
            client.setClientID(rs.getInt("clientID"));
            client.setEmail(rs.getString("email"));
            client.setPassword(rs.getString("password"));
            client.setPhoneNumber(rs.getString("phoneNumber"));
            client.setUserName(rs.getString("userName"));
        }
        rs.close();
        stmt.close();
        con.close();
        return client;
    }

    public Client getClientByEmail(String email) throws ClassNotFoundException, SQLException {
        Connection con = DBmanager.createConnection();
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        String line = "select * from  client where email = '" + email + "';";
        rs = stmt.executeQuery(line);
        if (rs.next() == false) {
            return null;
        }
        Client res = getClient(rs.getInt("clientID"));
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public boolean addClient(Client client) throws ClassNotFoundException, SQLException {
        Connection con = DBmanager.createConnection();
        Statement stmt = con.createStatement();
        String line = "insert into client (email,phoneNumber,userName,password) values ('" + client.getEmail() + "','" + client.getPhoneNumber()
                + "','" + client.getUserName() + "','" + client.getPassword() + "');";
        int row = stmt.executeUpdate(line);
        boolean res = row > 0;
        stmt.close();
        con.close();
        return res;
    }

    public void updateClient(Client client) throws ClassNotFoundException, SQLException {
        Connection con = DBmanager.createConnection();
        Statement Stmt = con.createStatement();
        System.out.println(client);
        System.out.println(client.getClientID());
        System.out.println(client.getEmail());
        System.out.println(client.getPhoneNumber());
        System.out.println(client.getPassword());
        String query = String.format("UPDATE client\n"
                + "Set email = '%s',phoneNumber = '%s',userName='%s',password='%s'\n"
                + "WHERE clientID = %d;", client.getEmail(), client.getPhoneNumber(), client.getUserName(), client.getPassword(), client.getClientID());
        System.out.println(query);
        Stmt.executeUpdate(query);
        con.close();
    }

}
