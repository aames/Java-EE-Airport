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
import javax.xml.ws.WebServiceRef;
import net.ajed.airport.beans.session.CustomerFlightInformationFacade;
import net.ajed.airport.dto.AddressDTO;
import net.ajed.airport.dto.CustomerDTO;
import net.ajed.airport.dto.FlightDTO;
import net.ajed.airport.dto.GateDTO;
import net.ajed.airport.dto.LuggageDTO;
import net.ajed.airport.webservice.weather.GlobalWeather;

/**
 *
 * @author Andrew
 */
@WebServlet(name = "EditServlet", urlPatterns = {"/EditServlet"})
public class EditServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/www.webservicex.net/globalweather.asmx.wsdl")
    private GlobalWeather service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     */
    @Inject
    CustomerFlightInformationFacade cif;

    @SuppressWarnings("CallToThreadDumpStack")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String weather = "";
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("actionid");

        Integer cid = null;
        String updateAction = request.getParameter("updateaction");

        if ("apply".equalsIgnoreCase(updateAction)) {
            try {
                cid = Integer.parseInt(request.getParameter("custid"));
                String fname = request.getParameter("custfname");
                String sname = request.getParameter("custsname");
                Integer passportid = Integer.parseInt(request.getParameter("custpassport"));
                Integer luggageid = Integer.parseInt(request.getParameter("custluggage"));
                Integer flightid = Integer.parseInt(request.getParameter("custflight"));
                Integer addressid = Integer.parseInt(request.getParameter("custaddress"));

                System.out.println("Updating customer: " + cid);
                cif.updateCustomerDetails(cid, fname, sname, passportid, luggageid, flightid, addressid);
                request.setAttribute("selectedcust", cif.getCustomerAsDTO(cid));
                request.setAttribute("luggage", cif.getLuggageAsDTO());
                request.setAttribute("flight", cif.getFlightAsDTO());
                request.setAttribute("gate", cif.getGateAsDTO());
                request.setAttribute("address", cif.getAddressAsDTO());
                if (cif.getFlightAsDTO() != null) {
                    weather = getWeather(cif.getFlightAsDTO().getDestination(), "");
                }
                request.setAttribute("weather", weather);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        try {
            cid = Integer.parseInt(request.getParameter("queryid"));
            //System.out.println(cid);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }

        //List<CustomerDTO> customers = new ArrayList<>(cf.findAllAsDTO());
        System.out.println("Action is " + action);
        CustomerDTO selectedcust = null;
        if ("search customers".equalsIgnoreCase(action)) {
            selectedcust = cif.getCustomerAsDTO(cid);
            request.setAttribute("selectedcust", selectedcust);
        } else {
        }
        // Load flight data
        FlightDTO flight = null;

        LuggageDTO luggage = null;
        AddressDTO address = null;
        GateDTO gate = null;

        if (selectedcust != null) {
            System.out.println(selectedcust.getFlight() + " is the selected cust flight");
            System.out.println(selectedcust);

            if (selectedcust.getFlight() != null || cif.getFlightAsDTO() != null) {
                flight = cif.getFlightAsDTO();
                request.setAttribute("flight", flight);
            }

            if (flight != null) {
                weather = getWeather(flight.getDestination(), "");
                if (flight.getGate() != null) {

                    gate = cif.getGateAsDTO();
                    request.setAttribute("gate", gate);
                }
            }
            if ("".equals(weather)) {
                request.setAttribute("Weather not found for Flight destination, Sorry!", weather);
            } else {
                request.setAttribute("weather", weather);
            }

            if (selectedcust.getLuggage() != null || cif.getLuggageAsDTO() != null) {
                luggage = cif.getLuggageAsDTO();
                request.setAttribute("luggage", luggage);
            }

            if (selectedcust.getAddress() != null || cif.getAddressAsDTO() != null) {
                address = cif.getAddressAsDTO();
                request.setAttribute("address", address);
            }

        }
//        if (flight != null){
//            //request.setAttribute("custflight", );
//            request.setAttribute("flight", flight);
//        }

        request.getRequestDispatcher("customerlookup.jsp").forward(request, response);

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

    private String getWeather(java.lang.String cityName, java.lang.String countryName) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        net.ajed.airport.webservice.weather.GlobalWeatherSoap port = service.getGlobalWeatherSoap();
        return port.getWeather(cityName, countryName);
    }

}
