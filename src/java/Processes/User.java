 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author abdom
 */
public class User {
    
    private String userName;
    //private String secondName;
    private String email;
    private String phoneNumber; 
    private String password;
    private String gender;
    
    //public Map<String,String> user;
    
    public User( Map<String,String> userInfo ){
        this.userName = userInfo.get("userName");
        this.password = userInfo.get("password");
        this.email = userInfo.get("email");
        this.phoneNumber = userInfo.get("phoneNumber");
    }

    User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void display(){
        System.out.println(userName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(password);
        System.out.println(" ******** ");
    }
    
    public ArrayList<String> getInfoList(){
        ArrayList<String> values = new ArrayList<>();
        values.add(email);
        values.add(phoneNumber);
        values.add(password);
        values.add(userName);
        return values;
    }
    public Map<String,String> getUserInfo(){
        Map<String,String> userInfo = new HashMap<>();
        userInfo.put("userName",this.userName);
        //userInfo.put("secondName",this.secondName);
        userInfo.put("email",this.email);
        userInfo.put("phoneNumber",this.phoneNumber);
        userInfo.put("password",this.password);
        return userInfo;
    }
    
   
            
    
    
}
