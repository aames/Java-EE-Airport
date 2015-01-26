/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ajed.airport.beans.session.CustomerFacade;
import net.ajed.airport.beans.session.CustomerFlightInformationFacade;
import net.ajed.airport.beans.session.FlightFacade;
import net.ajed.airport.beans.session.GateFacade;
import net.ajed.airport.entity.Customer;
import net.ajed.airport.entity.Flight;
import net.ajed.airport.entity.Gate;

/**
 *
 * @author Andrew
 */
@WebServlet(name = "ThreeQueries", urlPatterns = {"/ThreeQueries"})
public class ThreeQueries extends HttpServlet {

    @Inject
    CustomerFacade cf;
    @Inject
    FlightFacade ff;
    @Inject
    GateFacade gf;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ThreeQueries</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ThreeQueries Printing Customers at " + request.getContextPath() + "</h1>");
            printAllCustomers(response);
            out.println("<h1>Servlet ThreeQueries Printing Flights at " + request.getContextPath() + "</h1>");
            printAllFlights(response);
            out.println("<h1>Servlet ThreeQueries Printing Gates at " + request.getContextPath() + "</h1>");
            printAllGates(response);
            out.println("<h1>Servlet ThreeQueries Printing Customers on Flight ID 1 at " + request.getContextPath() + "</h1>");
            printCustomersOnAFlight(response);
            out.println("<h1>Servlet ThreeQueries Printing Customers on Flight ID 7 Without Luggage at " + request.getContextPath() + "</h1>");
            printCustomersOnAFlightWithoutLuggage(response);
            
            out.println("<h1>Servlet ThreeQueries Printing Gate Name from Luggage ID (VIA Luggage -> Customer -> Flight -> Gate) " + request.getContextPath() + "</h1>");
            printGateFromLuggage(response);
            
            out.println("<h1>Servlet ThreeQueries Printing Gate Name from Address ID (VIA Address -> Customer -> Flight -> Gate) " + request.getContextPath() + "</h1>");
            printGateNameFromAddress(response);
            
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    public void printAllCustomers(HttpServletResponse response) {
        List<Customer> customers = cf.findAll();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            for (Customer c : customers) {
                out.println(c.toString() + "<br>");
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreeQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //out.close();
        }
    }
    
    public void printAllFlights(HttpServletResponse response){
        List<Flight> flights = ff.findAll();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            for (Flight f: flights) {
                out.println("ID: "+ f.getId()+ " DESTINATION: " + f.getDestination()+ "<br>");
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreeQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //out.close();
        }
    }
    
    public void printAllGates(HttpServletResponse response){
        List<Gate> gates = gf.findAll();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            for (Gate g: gates) {
                out.println("ID: "+ g.getId()+ " NAME: " + g.getName()+ "<br>");
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreeQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //out.close();
        }
    }
    
    public void printCustomersOnAFlight(HttpServletResponse response){
        try {
            List<Customer> customers = (List)cif.getCustomersFromFlight(Integer.parseInt("1"));
            PrintWriter out = null;
            out = response.getWriter();
            for (Customer c: customers){
                out.println(c.toString() + "<br>");
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreeQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void printCustomersOnAFlightWithoutLuggage(HttpServletResponse response){
        try {
            List<Customer> customers = (List)cif.getCustomersFromFlightWithoutBaggage(Integer.parseInt("7"));
            PrintWriter out = null;
            out = response.getWriter();
            for (Customer c: customers){
                out.println(c.toString() + "<br>");
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreeQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printGateFromLuggage(HttpServletResponse response){
        try {
            PrintWriter out = null;
            out = response.getWriter();
            out.println("GATE NAME: " + cif.getGateNameFromLuggageId(Integer.parseInt("2")));
            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreeQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void printGateNameFromAddress(HttpServletResponse response){
        try {
            PrintWriter out = null;
            out = response.getWriter();
            List<String> gateNames = cif.getGateNamesFromAddressId(Integer.parseInt("5"));
            for (String s: gateNames){
                out.println("GATE NAME: " + s + "<br>");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreeQueries.class.getName()).log(Level.SEVERE, null, ex);
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
