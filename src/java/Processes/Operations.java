/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Map;
import java.util.Properties;
import java.util.Random;


/**
 *
 * @author abdom
 */
public class Operations {

    public static String generatePassword() {
        String password = "";
        int passLength = 10;
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        password += lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password += capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password += specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password += numbers.charAt(random.nextInt(numbers.length()));
        for (int i = 0; i < 10; i++) {
            password += combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    public static boolean sendEmail(User user)  {

        System.out.println(" in SendMail operation ");
        
        String from = "bedoronaldo77@gmail.com";
        String password = "bedo909090";
        String body = "Your password "+ user.getUserInfo().get("password");
        System.out.println(user.getUserInfo().get("email") + " : : : : <<");
        sendFromGMail(from,password,user.getUserInfo().get("email"),"activation",body);
        return true;
//    
    }

  
    
        private static void sendFromGMail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

    }
    
}
