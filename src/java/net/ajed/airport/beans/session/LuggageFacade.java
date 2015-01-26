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
import net.ajed.airport.dto.LuggageDTO;
import net.ajed.airport.entity.Luggage;

/**
 *
 * @author Andrew
 */
@Stateless
public class LuggageFacade extends AbstractFacade<Luggage> {
    @PersistenceContext(unitName = "AirportEE-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LuggageFacade() {
        super(Luggage.class);
    }
    public void create(Integer id){
        Luggage l = new Luggage(id);
        em.persist(l);
    }
    public void create(Integer id, Double mass){
        Luggage l = new Luggage(id, mass);
        em.persist(l);
    }
    public void edit(Integer id){
        Luggage l = em.find(Luggage.class, id);
        l.setId(id);
        em.merge(l);
    }
    public void edit(Integer id, Double mass){
        Luggage l = em.find(Luggage.class, id);
        l.setMass(mass);
        em.merge(l);
    }
    public void remove(Integer id){
        em.remove(em.find(Luggage.class, id));
    }
    public Luggage find(Integer id){
        return em.find(Luggage.class, id);
    }
    public LuggageDTO findAsDTO(Integer id){
        Luggage l = em.find(Luggage.class, id);
        return new LuggageDTO(l.getId(), l.getMass());
    }
    public List<LuggageDTO> findAllAsDTO(){
        List<Luggage> listOfLuggage = new ArrayList<>(em.createNamedQuery("Luggage.findAll", Luggage.class).getResultList());
        List<LuggageDTO> luggageasDTOs = new ArrayList<>();
        for (Luggage l: listOfLuggage){
            LuggageDTO curr = new LuggageDTO(l.getId(), l.getMass());
            luggageasDTOs.add(curr);
        }
        return luggageasDTOs;
    }
    @Override
    public List<Luggage> findAll(){
        List<Luggage> llist = new ArrayList<>(em.createNamedQuery("Luggage.findAll", Luggage.class).getResultList());
        return llist;
    }
    @Override
    public int count(){
        return findAll().size();
    }
}
