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
import net.ajed.airport.dto.AddressDTO;
import net.ajed.airport.entity.Address;

/**
 *
 * @author Andrew
 */
@Stateless
public class AddressFacade extends AbstractFacade<Address> {

    @PersistenceContext(unitName = "AirportEE-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }

    public void create(Integer id, String nameornumber, String street, String towncity, String statecounty, String country, String postalcode) {
        Address a = new Address(id);
        a.setNameornumber(nameornumber);
        a.setStreet(street);
        a.setTowncity(towncity);
        a.setStatecounty(statecounty);
        a.setCountry(country);
        a.setPostalcode(postalcode);
        em.persist(a);
    }

    public void edit(Integer id, String nameornumber, String street, String towncity, String statecounty, String country, String postalcode) {
        Address a = em.find(Address.class, id);
        a.setNameornumber(nameornumber);
        a.setStreet(street);
        a.setTowncity(towncity);
        a.setStatecounty(statecounty);
        a.setCountry(country);
        a.setPostalcode(postalcode);
        em.merge(a);
    }

    public void editNameOrNumber(Integer id, String nameornumber) {
        Address a = em.find(Address.class, id);
        a.setNameornumber(nameornumber);
        em.merge(a);
    }

    public void editStreet(Integer id, String street) {
        Address a = em.find(Address.class, id);
        a.setStreet(street);
        em.merge(a);
    }

    public void editTownCity(Integer id, String towncity) {
        Address a = em.find(Address.class, id);
        a.setTowncity(towncity);
        em.merge(a);
    }

    public void editStateCounty(Integer id, String statecounty) {
        Address a = em.find(Address.class, id);
        a.setStatecounty(statecounty);
        em.merge(a);
    }

    public void editCountry(Integer id, String country) {
        Address a = em.find(Address.class, id);
        a.setCountry(country);
        em.merge(a);
    }

    public void editPostCode(Integer id, String postalcode) {
        Address a = em.find(Address.class, id);
        a.setPostalcode(postalcode);
        em.merge(a);
    }

    public void remove(Integer id) {
        Address a = em.find(Address.class, id);
        em.remove(a);
    }

    public Address find(Integer id) {
        return em.find(Address.class, id);
    }

    public AddressDTO findAsDTO(Integer id) {
        Address a = em.find(Address.class, id);
        String name = null;
        String street = null;
        String town = null;
        String state = null;
        String country = null;
        String postcode = null;
        if (a.getNameornumber() != null) {
            name = a.getNameornumber();
        }
        if (a.getStreet() != null) {
            street = a.getStreet();
        }
        if (a.getTowncity() != null) {
            town = a.getTowncity();
        }
        if (a.getStatecounty() != null) {
            state = a.getStatecounty();
        }
        if (a.getCountry() != null) {
            country = a.getCountry();
        }
        if (a.getPostalcode() != null) {
            postcode = a.getPostalcode();
        }

        return (new AddressDTO(a.getId(), name, street, town, state, country, postcode));

    }

    @Override
    public List<Address> findAll() {
        return em.createNamedQuery("Address.findAll", Address.class).getResultList();
    }

    public List<AddressDTO> findAllAsDTO() {
        List<Address> addresses = findAll();
        List<AddressDTO> addAsDTOs = new ArrayList<>();
        for (Address a : addresses) {
            String name = null;
            String street = null;
            String town = null;
            String state = null;
            String country = null;
            String postcode = null;
            if (a.getNameornumber() != null) {
                name = a.getNameornumber();
            }
            if (a.getStreet() != null) {
                street = a.getStreet();
            }
            if (a.getTowncity() != null) {
                town = a.getTowncity();
            }
            if (a.getStatecounty() != null) {
                state = a.getStatecounty();
            }
            if (a.getCountry() != null) {
                country = a.getCountry();
            }
            if (a.getPostalcode() != null) {
                postcode = a.getPostalcode();
            }
            {
                addAsDTOs.add(new AddressDTO(a.getId(), name, street, town, state, country, postcode));
            }
        }
        return addAsDTOs;
    }

    @Override
    public int count() {
        return findAll().size();
    }
}
