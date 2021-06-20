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
@WebServlet(name = "UpdateHotel", urlPatterns = {"/UpdateHotel"})
public class UpdateHotel extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateHotel</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateHotel at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /* TODO output your page here. You may use following sample code. */
           System.out.println("ana weslt");
            Integer id = Integer.parseInt(request.getParameter("id"));
            String contact = request.getParameter("contact");
            String facilities = request.getParameter("facilities");
            String location = request.getParameter("location");
            String des = request.getParameter("des");
            if(contact == null && facilities == null && location == null && des == null)
            {
                out.print("fail");
                return;
            }
            else if(contact == "" && facilities == "" && location == "" && des == "")
            {
                out.print("fail");
                return;
            }
            else if(id == null)
            {
                out.print("fail");
                return;
            }
            System.out.println("hix");
            Hotel hotel = new HotelDB().getHotel(id);
            if(contact != null && contact != "") hotel.setContactInfo(contact);
            if(facilities != null && facilities != "") hotel.setFacilities(facilities);
            if(location != null && location != "") hotel.setLocation(location);
            if(des != null && des != "") hotel.setDescription(des);
            if(new HotelDB().updateHotel(hotel))
            {
                System.out.println("sucess");
                out.println("sucess");
            }
            else{
                System.out.println("fail");
                out.println("fail");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateHotel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateHotel.class.getName()).log(Level.SEVERE, null, ex);
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
