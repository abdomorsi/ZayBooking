/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author suhai
 */
@WebServlet(name = "paymentReservation", urlPatterns = {"/paymentReservation"})
public class paymentReservation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             String reservationID = request.getParameter("id");
            int id = Integer.parseInt(reservationID);
            int hotelID = Integer.parseInt(request.getParameter("hotelID"));
            ReservationDB reservationDB = new ReservationDB();
            boolean check = reservationDB.checkForAdmin(hotelID, id);
            if (check) {
                Reservation reservation = new ReservationDB().getReservationByID(id);
                System.out.println("hiiiiiiiiiiiiiiiiiiiii             " + reservation);
                if(reservation == null)
                {

                    out.print("fail");
                    return;
                }
                reservation.setPayment(1);
                System.out.println("hi");
                if(new ReservationDB().updateReservation(reservation))
                    out.print("sucess");
                else
                {
                    System.out.println("hi1");
                    out.print("fail");
                }
            }else
                out.print("not exist");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
           out.print("fail");
        } catch (ClassNotFoundException ex) {
           out.print("fail");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            out.print("fail");
        } catch (ClassNotFoundException ex) {
            out.print("fail");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
