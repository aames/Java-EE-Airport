/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.beans.message;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.ajed.airport.entity.Customer;

/**
 *
 * @author Andrew
 */
@MessageDriven(name = "CustomerBean", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/dest")
})

public class CustomerMDB implements MessageListener {

    private Connection connection;
    @Resource
    private MessageDrivenContext context;
    @PersistenceContext(unitName = "AirportEE-warPU")
    private EntityManager em;

    public CustomerMDB() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Customer c = (Customer) objectMessage.getObject();
            persist(c);
            System.out.println("MDB: Customer added: " + c.getFirstname() + " " + c.getLastname());
        } catch (JMSException e) {

        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
}