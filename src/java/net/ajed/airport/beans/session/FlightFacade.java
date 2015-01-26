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
import javax.persistence.TypedQuery;
import net.ajed.airport.dto.FlightDTO;
import net.ajed.airport.entity.Flight;
import net.ajed.airport.entity.Gate;

/**
 *
 * @author Andrew
 */
@Stateless
public class FlightFacade extends AbstractFacade<Flight> {

    @PersistenceContext(unitName = "AirportEE-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlightFacade() {
        super(Flight.class);
    }

    public void create(Integer id, String destination) {
        Flight f = new Flight(id, destination);
        em.persist(f);
    }

    public void create(Integer id, String destination, Integer gateid) {
        Flight f = new Flight(id, destination, em.find(Gate.class, gateid));
        em.persist(f);
    }

    public void create(FlightDTO fdto) {
        if (fdto.getGate() != null) {
            Flight f = new Flight(fdto.getId(), fdto.getDestination(), em.find(Gate.class, fdto.getGate()));
            em.persist(f);
        } else if (fdto.getDestination() != null) {
            Flight f = new Flight(fdto.getId(), fdto.getDestination());
            em.persist(f);
        } else {
            Flight f = new Flight(fdto.getId());
            em.persist(f);
        }
    }

    public void edit(Integer id, Integer newid) {
        Flight f = em.find(Flight.class, id);
        f.setId(newid);
        em.merge(f);
    }

    public void edit(Integer id, String destination) {
        Flight f = em.find(Flight.class, id);
        f.setDestination(destination);
        em.merge(f);
    }

    public void edit(Integer id, String destination, Integer gateid) {
        Flight f = em.find(Flight.class, id);
        f.setDestination(destination);
        f.setGateid(em.find(Gate.class, gateid));
        em.merge(f);
    }

    public void edit(FlightDTO fdto) {
        Flight f = em.find(Flight.class, fdto.getId());
        f.setDestination(fdto.getDestination());
        f.setGateid(em.find(Gate.class, fdto.getGate()));
        em.merge(f);
    }

    public void remove(Integer id) {
        Flight f = em.find(Flight.class, id);
        em.remove(f);
    }

    public void remove(String destination) {
        TypedQuery<Flight> q = em.createNamedQuery("Flight.findByDestination", Flight.class);
        q.setParameter("destination", destination);
        List<Flight> flightlist = q.getResultList();
        for (Flight f : flightlist) {
            em.remove(f);
        }
    }

    public Flight find(Integer id) {
        return em.find(Flight.class, id);
    }

    public FlightDTO findAsDTO(Integer id) {
        Flight f = em.find(Flight.class, id);
        if (f.getGateid() == null) {
            return (new FlightDTO(f.getId(), f.getDestination()));
        } else {
            return (new FlightDTO(f.getId(), f.getDestination(), f.getGateid().getId()));
        }
    }

    @Override
    public List<Flight> findAll() {
        return em.createNamedQuery("Flight.findAll", Flight.class).getResultList();
    }

    public List<FlightDTO> findAllAsDTO() {
        List<Flight> listOfFlights = new ArrayList<>(em.createNamedQuery("Flight.findAll", Flight.class).getResultList());
        List<FlightDTO> flightsasDTOs = new ArrayList<>();
        for (Flight f : listOfFlights) {
            if (f.getGateid() == null) {
                flightsasDTOs.add(new FlightDTO(f.getId(), f.getDestination()));
            } else {
                flightsasDTOs.add(new FlightDTO(f.getId(), f.getDestination(), f.getGateid().getId()));
            }

        }
        return flightsasDTOs;
    }

    @Override
    public int count() {
        return findAll().size();
    }
}
