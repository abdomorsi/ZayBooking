/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;


public class Admin {
    
    private int adminID;
    private String password;
    private String email;
    private String adminName;

    public Admin(){}

    public Admin(int adminID, String password, String email, String adminName) {
        this.adminID = adminID;
        this.password = password;
        this.email = email;
        this.adminName = adminName;
    }
    
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
}
