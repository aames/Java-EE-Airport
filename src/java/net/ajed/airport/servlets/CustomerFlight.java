/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.servlets;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ajed.airport.beans.session.CustomerFlightInformationFacade;

/**
 *
 * @author Andrew
 */
@WebServlet(name = "CustomerFlight", urlPatterns = {"/CustomerFlight"})
public class CustomerFlight extends HttpServlet {

    @Inject
    CustomerFlightInformationFacade cif;

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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CustomerFlight</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CustomerFlight at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String action = request.getParameter("create");
        try{
            if (action != null){
            Integer custid = Integer.parseInt(request.getParameter("customerid"));
            String custfname = request.getParameter("customerfname");
            String custsname = request.getParameter("customersname");
            Integer flightid = Integer.parseInt(request.getParameter("flightid"));
            String flightdest = request.getParameter("flightdestination");
            Integer addressid = Integer.parseInt(request.getParameter("addressid"));
            String addNoN = request.getParameter("addressnameornumber");
            String addstreet = request.getParameter("addressstreet");
            String addtown = request.getParameter("addresstown");
            String addstate = request.getParameter("addressstate");
            String addcountry = request.getParameter("addresscountry");
            String addpostcode = request.getParameter("addresspostcode");
            Integer gateid = Integer.parseInt(request.getParameter("gateid"));
            String gatename = request.getParameter("gatename");
            Integer luggageid = Integer.parseInt(request.getParameter("luggageid"));
            Double luggmass = Double.parseDouble(request.getParameter("luggagemass"));
            cif.createCustomerAddressFlightGateLuggage(custid, custfname, custsname, addressid,
                    addNoN, addstreet, addtown, addstate, addcountry, addpostcode, flightid, 
                    flightdest, gateid, gatename, luggageid, luggmass);
            request.setAttribute("selectedcust", cif.getCustomerAsDTO(custid));
            request.setAttribute("luggage", cif.getLuggageAsDTO());
            request.setAttribute("flight", cif.getFlightAsDTO());
            request.setAttribute("gate", cif.getGateAsDTO());
            request.setAttribute("address", cif.getAddressAsDTO());
        } else {
                
            }
        } catch (NumberFormatException e) {
            System.out.println("CustomerFlight: Create(): Details were not filled in correctly.");
        }
        
        
        
        request.getRequestDispatcher("/custflightcreate.jsp").forward(request, response);

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
