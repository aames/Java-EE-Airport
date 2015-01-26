/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.beans.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import net.ajed.airport.dto.AddressDTO;
import net.ajed.airport.dto.CustomerDTO;
import net.ajed.airport.dto.FlightDTO;
import net.ajed.airport.dto.GateDTO;
import net.ajed.airport.dto.LuggageDTO;
import net.ajed.airport.entity.Customer;
import net.ajed.airport.entity.Flight;

/**
 *
 * @author Andrew
 */
@Stateless
@LocalBean
public class CustomerFlightInformationFacade {

    @Inject
    CustomerFacade cf;
    @Inject
    AddressFacade af;
    @Inject
    LuggageFacade lf;
    @Inject
    FlightFacade ff;
    @Inject
    GateFacade gf;

    private CustomerDTO customer;
    private FlightDTO flight = null;
    private LuggageDTO luggage = null;
    private AddressDTO address = null;
    private GateDTO gate = null;

    public CustomerDTO getCustomerAsDTO(Integer id) {
        for (CustomerDTO c : cf.findAllAsDTO()) {
            if (id.intValue() == c.getId().intValue()) {

                this.customer = c;
                if (c.getFlight() != null) {
                    this.flight = ff.findAsDTO(c.getFlight());
                }

                if (c.getLuggage() != null) {
                    this.luggage = lf.findAsDTO(c.getLuggage());
                }
                if (c.getAddress() != null) {
                    this.address = af.findAsDTO(c.getAddress());
                }
                if (c.getFlight() != null && gf.findAsDTO(flight.getGate()) != null) {
                    this.gate = gf.findAsDTO(flight.getGate());
                }
                return c;
            }
        }
        return null;
    }

    public FlightDTO getFlightAsDTO() {

        return flight;
    }

    public GateDTO getGateAsDTO() {
        return gate;
    }

    public LuggageDTO getLuggageAsDTO() {
        return luggage;
    }

    public AddressDTO getAddressAsDTO() {
        return address;
    }

    public Collection<Customer> getCustomersFromFlight(Integer id) {
        try {
            List<Customer> customers = cf.findAll();
            Collection<Customer> custsToRtn = new ArrayList<>();
            for (Customer c : customers) {
                if (c.getFlightid() != null && c.getFlightid().getId().intValue() == id.intValue()) {
                    custsToRtn.add(c);

                }
            }
            return custsToRtn;
        } catch (Exception e) {

        }
        return null;
    }

    public Collection<Customer> getCustomersFromFlightWithBaggage(Integer id) {
        try {
            List<Customer> customers = cf.findAll();
            Collection<Customer> custsToRtn = new ArrayList<>();
            for (Customer c : customers) {
                if (c.getFlightid() != null && c.getFlightid().getId().intValue() == id.intValue()) {
                    if (c.getLuggageid() != null) {

                        custsToRtn.add(c);
                    } 
                }
            }
            return custsToRtn;
        } catch (Exception e) {

        }
        return null;
    }
        public Collection<Customer> getCustomersFromFlightWithoutBaggage(Integer id) {
        try {
            List<Customer> customers = cf.findAll();
            Collection<Customer> custsToRtn = new ArrayList<>();
            for (Customer c : customers) {
                if (c.getFlightid() != null && c.getFlightid().getId().intValue() == id.intValue()) {
                    if (c.getLuggageid() != null) {
                        
                    } 
                    else{
                        custsToRtn.add(c);
                    }
                }
            }
            return custsToRtn;
        } catch (Exception e) {

        }
        return null;
    }
    public Customer getCustomerFromLuggage(Integer luggageid){
        List<Customer> customers = cf.findAll();
        Customer curr = null;
        for (Customer c:customers){
            if (c.getLuggageid() != null){
                if (c.getLuggageid().getId().intValue() == luggageid.intValue()){
                    return c;
                }
            }
        }
        return curr;
    }
    public String getGateFromLuggage(Customer c){
        if (c != null){
            if (c.getFlightid() != null){
                if (c.getFlightid().getGateid() != null){
                    return c.getFlightid().getGateid().getName();
                }
            }
        }
        return null;
    }
    public String getGateNameFromLuggageId(Integer luggageid){
        return getGateFromLuggage(getCustomerFromLuggage(luggageid));
    }
    
    public List<String> getGateNamesFromAddressId(Integer addressid){
        List<Customer> customers = cf.findAll();
        List<Flight> listOfFlights = new ArrayList<>();
        List<String> listOfGates = new ArrayList<>();
        for (Customer c: customers){
            if (c.getAddressid() != null && c.getAddressid().getId().intValue() == addressid.intValue()){
                if (c.getFlightid() != null){
                   listOfFlights.add(c.getFlightid()); 
                }
            }
        }
        for (Flight f: listOfFlights){
            if (f.getGateid() != null){
                listOfGates.add(f.getGateid().getName());
            }
        }
        if (listOfGates.isEmpty()){
            return null;
        } else {
            return listOfGates;
        }
        
    }

    public void updateCustomerDetails(Integer cid, String fname, String sname, Integer passportid, Integer luggageid, Integer flightid, Integer addressid) {
        cf.edit(cid, fname, sname, passportid);
        cf.edit(cid, ff.find(flightid));
        cf.edit(cid, af.find(addressid));
        cf.edit(cid, lf.find(luggageid));
        
    }
    public void createCustomerAddressFlightGateLuggage(Integer customerid, 
            String customerfname, String customersname, Integer addressid, 
            String nameornumber, String street, String towncity, String statecounty, 
            String country, String postcode, Integer flightid, String flightname, 
            Integer gateid, String gatename, Integer luggageid, Double luggagemass){
        
        cf.create(customerid, customerfname, customersname);
        af.create(addressid, nameornumber, street, towncity, statecounty, country, postcode);
        ff.create(flightid, flightname);
        gf.create(gateid, gatename);
        lf.create(luggageid, luggagemass);
        
        cf.edit(customerid, af.find(addressid));
        cf.edit(customerid, ff.find(flightid));
        cf.edit(customerid, lf.find(luggageid));
        
        ff.edit(flightid, flightname, gateid);
        
    }
}
