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
import net.ajed.airport.dto.GateDTO;
import net.ajed.airport.entity.Gate;

/**
 *
 * @author Andrew
 */
@Stateless
public class GateFacade extends AbstractFacade<Gate> {
    @PersistenceContext(unitName = "AirportEE-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GateFacade() {
        super(Gate.class);
    }
    public void create(Integer id){
        Gate g = new Gate(id);
        em.persist(g);
    }
    public void create(Integer id, String name){
        Gate g = new Gate(id, name);
        em.persist(g);
    }
    public void edit(Integer id, String name){
        Gate g = em.find(Gate.class, id);
        g.setName(name);
        em.merge(g);
    }
    public void edit(Integer oldid, Integer newid){
        Gate g = em.find(Gate.class, oldid);
        g.setId(newid);
        em.merge(g);
    }
    public void edit(GateDTO gdto){
        Gate g = em.find(Gate.class, gdto.getId());
        g.setName(gdto.getName());
        em.merge(g);
    }
    public void remove(Integer id){
        em.remove(em.find(Gate.class, id));
    }
    public Gate find(Integer id){
        return em.find(Gate.class, id);
    }
    public GateDTO findAsDTO(Integer id){
        Gate g = em.find(Gate.class, id);
        return (new GateDTO(g.getId(), g.getName()));
    }
    @Override
    public List<Gate> findAll(){
        return em.createNamedQuery("Gate.findAll", Gate.class).getResultList();
    }
    public List<GateDTO> findAllAsDTO(){
        List<Gate> listOfFlights = new ArrayList<>(em.createNamedQuery("Gate.findAll", Gate.class).getResultList());
        List<GateDTO> gatessasDTOs = new ArrayList<>();
        for (Gate g: listOfFlights){
            gatessasDTOs.add(new GateDTO(g.getId(), g.getName()));
        }
        return gatessasDTOs;
    }
    @Override
    public int count(){
        return findAll().size();
    }
}
