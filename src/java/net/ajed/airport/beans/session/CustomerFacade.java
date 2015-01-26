/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.beans.session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.ajed.airport.dto.CustomerDTO;
import net.ajed.airport.entity.Address;
import net.ajed.airport.entity.Customer;
import net.ajed.airport.entity.Flight;
import net.ajed.airport.entity.Luggage;

/**
 *
 * @author Andrew
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "AirportEE-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    public void create(Integer id) {
        Customer c = new Customer(id);
        em.persist(c);
    }

    public void create(Integer id, String fname, String sname) {
        Customer c = new Customer(id, fname, sname);
        em.persist(c);
    }

    public void create(Integer id, String fname, String sname, Integer passportnum) {
        Customer c = new Customer(id, fname, sname);
        c.setPassportnumber(passportnum);
        em.persist(c);
    }

    public void create(Integer id, String fname, String sname, Integer passportnum, Address address, Luggage luggage, Flight flight) {
        Customer c = new Customer(id, fname, sname);
        c.setPassportnumber(passportnum);
        c.setAddressid(address);
        c.setLuggageid(luggage);
        c.setFlightid(flight);
        em.persist(c);
    }

    public void edit(Integer id) {
        Customer c = em.find(Customer.class, id);
        c.setId(id);
        em.merge(c);
    }

    public void edit(Integer id, String fname, String sname, Integer passportnumber) {
        Customer c = em.find(Customer.class, id);
        c.setFirstname(fname);
        c.setLastname(sname);
        c.setPassportnumber(passportnumber);
    }

    public void edit(Integer id, Address address) {
        Customer c = em.find(Customer.class, id);
        c.setAddressid(address);
        em.merge(c);
    }

    public void edit(Integer id, Luggage luggage) {
        Customer c = em.find(Customer.class, id);
        c.setLuggageid(luggage);
        em.merge(c);
    }

    public void edit(Integer id, Flight flight) {
        Customer c = em.find(Customer.class, id);
        c.setFlightid(flight);
        em.merge(c);
    }

    public void remove(Integer id) {
        em.remove(em.find(Customer.class, id));
    }

    public Customer find(Integer id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public List<CustomerDTO> findAllAsDTO() {
        List<Customer> listc = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
        List<CustomerDTO> listdto = new ArrayList<>();
        for (Customer c : listc) {
            Integer luggage = null;
            Integer flight = null;
            Integer address = null;
            if (c.getFlightid() != null) {
                flight = c.getFlightid().getId();
            }
            if (c.getLuggageid() != null) {
                luggage = c.getLuggageid().getId();

            }
            if (c.getAddressid() != null) {
                address = c.getAddressid().getId();
            }
            listdto.add(new CustomerDTO(c.getId(), c.getFirstname(), c.getLastname(), c.getPassportnumber(), luggage, flight, address));

        }
        return listdto;
    }

    @Override
    public int count() {
        return findAll().size();
    }
}
