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
@WebServlet(name = "updateRoom", urlPatterns = {"/updateRoom"})
public class updateRoom extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            System.out.println("ana weslt");
            int id = Integer.parseInt(request.getParameter("id"));
            int hotelID = Integer.parseInt(request.getParameter("hotelID"));
            RoomDB roomDB = new RoomDB();
            Room room = roomDB.getRoom(id,hotelID);
            String kind = request.getParameter("kind");
            String num = request.getParameter("num");
            String des = request.getParameter("des");
            String child = request.getParameter("child");
            String adult = request.getParameter("adult");
            String facilities = request.getParameter("facilities");
            String price = request.getParameter("price");
            System.out.println("hix");
            //Room room = new Room(id,hotelID);
            if (kind != null && !kind.equals("")){
                room.setKind(kind);
            }
            if (num != null && !num.equals("")){
                room.setNumOfRooms(Integer.parseInt(num));
            }
            if (des != null && !des.equals("")){
                room.setDescription(des);
            }
            if (child != null && !child.equals("")){
                room.setNumOfChildren(Integer.parseInt(child));
            }
            if (adult != null && !adult.equals("")){
                room.setNumOfAdults(Integer.parseInt(adult));
            }
            if (facilities != null && !facilities.equals("")){
                room.setFacilities(facilities);
            }
            if (price != null && !price.equals("")){
                room.setPrice(Double.parseDouble(price));
            }
            if(new RoomDB().updateRoom(room))
            {
                System.out.println("sucess");
                out.println("sucess");
            }
            else{
                System.out.println("fail");
                out.println("fail");
            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updateRoom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(updateRoom.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updateRoom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(updateRoom.class.getName()).log(Level.SEVERE, null, ex);
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
