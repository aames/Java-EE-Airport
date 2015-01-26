package net.ajed.airport.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.ajed.airport.model.Address;
import net.ajed.airport.model.Flight;
import net.ajed.airport.model.Luggage;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-01-05T19:35:37")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, Integer> id;
    public static volatile SingularAttribute<Customer, Integer> passportnumber;
    public static volatile SingularAttribute<Customer, Address> addressid;
    public static volatile SingularAttribute<Customer, Luggage> luggageid;
    public static volatile SingularAttribute<Customer, String> lastname;
    public static volatile SingularAttribute<Customer, String> firstname;
    public static volatile SingularAttribute<Customer, Flight> flightid;
    public static volatile CollectionAttribute<Customer, Luggage> luggageCollection;

}