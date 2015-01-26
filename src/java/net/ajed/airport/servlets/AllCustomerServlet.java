/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.servlets;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ajed.airport.beans.session.CustomerFacade;
import net.ajed.airport.dto.CustomerDTO;

/**
 *
 * @author Andrew
 */
@WebServlet(name = "AllCustomerServlet", urlPatterns = {"/AllCustomerServlet"})
public class AllCustomerServlet extends HttpServlet {

    @Inject
    CustomerFacade cf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CustomerDTO> customers;
        customers = cf.findAllAsDTO();
        request.setAttribute("customers", customers); // This sets ${customers}

        
        String rawid = request.getParameter("custid");
        
        
        
        String action = request.getParameter("action");
        
        
        System.out.println("Parameter values containing Select are: " + request.getParameterValues("Select"));
        

        Integer id = null, pid = null;
        if (rawid != null) {
            id = Integer.parseInt(rawid);
        }
        
        
        String fname = request.getParameter("custfname");
        String sname = request.getParameter("custsname");
        if (request.getParameter("custpassport") != null) {
            pid = (Integer.parseInt(request.getParameter("custpassport")));
        }
        
        if ("Create".equalsIgnoreCase(action)) {
            System.out.println("Processing create");
            if (id != null) {
                System.out.println("Creating user with: " + id + " "+ fname + " "+ sname + " " + pid);
                cf.create(id, fname, sname, pid);
            }
        } 

        request.getRequestDispatcher("/allcustomers.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
