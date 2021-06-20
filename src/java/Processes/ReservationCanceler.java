package Processes;

import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class ReservationCanceler {
    void cancelReservationByUser(int reservationID,String adminEmail) throws ClassNotFoundException, SQLException, Exception{
        ReservationDB reservationDB = new ReservationDB();
        boolean flag = reservationDB.cancelReservation(reservationID);
        if (flag){
            String message = "reservation with id = " + reservationID + "has been canceled";
            String subject = "reservation cancellation";
            javaMail.sendEmail(adminEmail,subject,message);
        }
    }
    
}
