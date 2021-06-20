/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdom
 */
public class DBmanager {
    
    public static Connection createConnection() throws ClassNotFoundException{
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/zaybooking","root","12345678");    
        } catch (SQLException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static ResultSet executeSqlQuery(String mysqlQuery, ArrayList<String> values) throws SQLException{
        ResultSet rs = null;
        try {
            Connection conn = createConnection();
            PreparedStatement ps = conn.prepareStatement(mysqlQuery);
            int len = values.size();
            System.out.println(len + " len in exe ");
            for ( int i = 1; i <= len ; i++ ){
                System.out.println(values.get(i - 1) + " in exe  ");
                ps.setString(i,values.get(i-1)); //?
            }
            rs = ps.executeQuery();
            //conn.close();
            //ps.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static void executeSqlUpdate(String mysqlQuery, ArrayList<String> values) throws SQLException{
        try {
            Connection conn = createConnection();
            PreparedStatement ps = conn.prepareStatement(mysqlQuery);
            int len = values.size();
            System.out.println(len + " len in exe upd  ");
            for ( int i = 1; i <= len ; i++ ){
                System.out.println(values.get(i - 1) + " in exe upd " + i);
                ps.setString(i,values.get(i-1)); //?
            }
            ps.executeUpdate();
            //conn.close();
            //ps.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Map <String,String> colsCondition
    public static ResultSet select(String tableName, ArrayList<String> selectedColumns, Map <String,String> columnsCondition) throws SQLException{
        ResultSet rs = null;
        try {
            Connection conn = createConnection();
            String sqlQuery = "SELECT ";
            
            for (int i = 0; i < selectedColumns.size() ; i++){
                sqlQuery += selectedColumns.get(i);
                if ( i < selectedColumns.size() - 1 ) {
                    sqlQuery += ',';
                }
            }
            
            sqlQuery += " FROM " + tableName + " WHERE ";
            
            int len = columnsCondition.size(), cnt = 1;
            
            for ( String col : columnsCondition.keySet() ){
                sqlQuery += col + "=?"; 
                if ( cnt < len ) sqlQuery += ',';
            }   
            
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            cnt = 1;
            for ( String val : columnsCondition.values() ){
                ps.setString(cnt,val);
                cnt++;
            }
            rs = ps.executeQuery();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static ArrayList<String> getColumnsNames( String tableName ) throws SQLException, ClassNotFoundException{
        ArrayList <String> colsNames = new ArrayList<>();
        String mysqlQuery = "SELECT * FROM " + tableName;
        Connection conn = createConnection();
        PreparedStatement ps = conn.prepareStatement(mysqlQuery);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metadata = rs.getMetaData();
        for ( int i = 1 ; i <= metadata.getColumnCount() ; i++){
            colsNames.add( metadata.getColumnName(i) );
        }        
        conn.close();
        ps.close();
        return (colsNames);
    }
    
    public static String insert(String tableName, ArrayList<String> values) throws SQLException, ClassNotFoundException{
        String sqlQuery = "INSERT INTO " + tableName + " (";
        //"INSERT INTO bankaccount (BankAccountID,BACreationDate,BACurrentBalance,CustomerID) VALUES(?,?,?,?)"
        ArrayList colsNames = getColumnsNames(tableName);
        int len = colsNames.size();
        for ( int i = 0; i < len ; i++ ){ // if i will not insert id make i = 1
            sqlQuery += colsNames.get(i);
            System.out.println(colsNames.get(i) + "  conls in insert "); 
            
            if( i < len-1 ) sqlQuery += ',';
        }
        
        sqlQuery+= " ) VALUES (";
        for ( int i = 0; i < len ; i++ ){
            sqlQuery += '?';
            if( i  < len-1 ) sqlQuery+=',';
        }
        sqlQuery+= ')';
        System.out.println(sqlQuery + " q >> insert ");
        executeSqlUpdate(sqlQuery,values);
        
        return sqlQuery;
    }
    
    public static String update(String tableName, ArrayList<String> values , String condition) throws SQLException, ClassNotFoundException{
        // "update user905 set name=?,password=?,email=?,country=? where id=?"
        String sqlQuery = "UPDATE " + tableName + " SET ";
        ArrayList colsNames = getColumnsNames(tableName);
        int len = colsNames.size();
        for ( int i = 1; i < len ; i++ ){
            sqlQuery += colsNames.get(i);
            sqlQuery += "=?";
            if( i < len-1 ) sqlQuery += ',';
        }
        sqlQuery += " WHERE id=?";
        
        for( int i = 0; i < values.size()-1 ; i++ ){
            Collections.swap(values,i,i+1);
        }
        
        // call execute
        return sqlQuery;
    }
    
    public static int getHowManyRecord(String tableName, String condition, ArrayList<String> values) throws ClassNotFoundException, SQLException{
        //SELECT COUNT(*) FROM employees WHERE emp_age>32;
        int c = 0;

        String sqlQuery = "SELECT COUNT(*) FROM " + tableName;
        if( !condition.equals("") ){
            sqlQuery += " WHERE ";
            sqlQuery += condition;
        }
        Connection conn = createConnection();
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        for( int i = 1 ; i <= values.size() ; i++ ){
            ps.setString(i, values.get(i-1));
        }
        ResultSet rs = ps.executeQuery();
        rs.next();
        c = rs.getInt(1);
        System.out.println( c + " c <<<");
        return c;
    }
    
}
