/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ajed.airport.beans.session.AddressFacade;
import net.ajed.airport.beans.session.CustomerFacade;
import net.ajed.airport.beans.session.FlightFacade;
import net.ajed.airport.beans.session.GateFacade;
import net.ajed.airport.beans.session.LuggageFacade;
import net.ajed.airport.entity.Address;
import net.ajed.airport.entity.Customer;
import net.ajed.airport.entity.Flight;
import net.ajed.airport.entity.Gate;
import net.ajed.airport.entity.Luggage;

/**
 *
 * @author Andrew
 */
@WebServlet(name = "PopulateDBServlet", urlPatterns = {"/PopulateDBServlet"})
public class PopulateDBServlet extends HttpServlet {
    @Inject CustomerFacade cf;
    @Inject FlightFacade ff;
    @Inject LuggageFacade lf;
    @Inject GateFacade gf;
    @Inject AddressFacade af;
    
    private void populate() {
        
        for (Customer c: cf.findAll()){
            cf.remove(c);
        }
        for(Address a: af.findAll()){
            af.remove(a);
        }
        for (Flight f: ff.findAll()){
            ff.remove(f);
        }
        for (Luggage l: lf.findAll()){
            lf.remove(l);
        }
        for (Gate g: gf.findAll()){
            gf.remove(g);
        }
        //id, nameornumber, street, towncity, statecounty, country, postalcode)
        af.create(1, "28", "Whiskey street", "Maltley", "Kentucky", "USA", "03030");
        af.create(2, "Old barn", "Hammersley ave", "Stamford", "London", "UK", "hh44kd");
        af.create(3, "2", "Main st", "Cheltenham", "", "UK", "ch3 7ty");
        af.create(4, "1", "Old st", "Aberdeen", "Aberdeenshire", "UK", "ab5 4ng");
        af.create(5, "349", "West avenue", "Sudbury", "Staffs", "UK", "rr4 2kk");
        af.create(6, "3", "Harding st", "Leeds", "Yorks", "UK", "ls4 43r");
        af.create(7, "293", "Rue m. et pierre curie", "paris", "Paris", "France", "01245");
        
        
        gf.create(1, "G1");
        gf.create(2, "G2a");
        gf.create(3, "G2b");
        gf.create(4, "G3");
        
        ff.create(1, "Sydney", 1);
        ff.create(2, "Auckland", 2);
        ff.create(3, "Brisbane", 2);
        ff.create(4, "Budapest", 1);
        ff.create(5, "Detroit", 4);
        ff.create(6, "Wellington", 3);
        ff.create(7, "Christchurch", 3);
        
        lf.create(1, 22.3);
        lf.create(2, 20.0);
        lf.create(3, 21.0);
        lf.create(4, 13.0);
        lf.create(5, 10.0);
        lf.create(6, 16.0);
        lf.create(7, 10.0);
        lf.create(8, 24.0);
        lf.create(9, 23.0);
        
        // ID, fname, sname, passportid, address, luggage, flight
        cf.create(1, "Derek", "Jones", 233, af.find(1), lf.find(9), ff.find(3));
        cf.create(2, "Hamish", "McCall", 1982, af.find(2), lf.find(8), ff.find(2));
        cf.create(3, "Jimmy", "Smith", 1233, af.find(4), lf.find(7), ff.find(1));
        cf.create(4, "Jane", "Yardley", 239, af.find(3), lf.find(6), ff.find(4));
        cf.create(5, "Andy", "Lester", 2132, af.find(5), lf.find(5), ff.find(4));
        cf.create(6, "Phillipe", "Tyrell", 34, af.find(6), lf.find(4), ff.find(5));
        cf.create(7, "LeBron", "James", 3423, af.find(5), lf.find(3), ff.find(6));
        cf.create(8, "Manuel", "Gonzalez", 30, af.find(6), lf.find(2), ff.find(4));
        cf.create(9, "Fred", "Smith", 6443, af.find(7), lf.find(1), ff.find(4));
        cf.create(10, "Harrison", "Townsend", 4440, af.find(7), null, ff.find(7));

        
    }

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
            //if (cf.findAll().size() < 5){
                populate();
           //}
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Populating Tables...</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Customers " + request.getContextPath() + "</h1>");
            out.println("" + cf.findAll().toString() + "<br>" );
            out.println("Customers: <br>");
            for (Customer c: cf.findAll()){
                out.println("ID: "+ c.getId().toString() + 
                            " NAME: " + c.getFirstname() +" " + c.getLastname() +  
                            " PASSPORT: "+  c.getPassportnumber() + " FLIGHT: " + c.getFlightid().getDestination() +
                            " GATE " +c.getFlightid().getGateid().getName());
                         if (c.getLuggageid() != null) {
                             out.println(" LUGGAGE WEIGHT " + c.getLuggageid().getMass()+"<br>" );
                         } else {
                             out.println(" NO LUGGAGE CARRIED <br>");
                         }
            }
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
