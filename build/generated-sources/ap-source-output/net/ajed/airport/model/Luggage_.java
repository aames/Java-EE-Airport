package net.ajed.airport.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.ajed.airport.model.Customer;
import net.ajed.airport.model.Flight;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-01-05T19:35:37")
@StaticMetamodel(Luggage.class)
public class Luggage_ { 

    public static volatile SingularAttribute<Luggage, Integer> id;
    public static volatile SingularAttribute<Luggage, Double> mass;
    public static volatile CollectionAttribute<Luggage, Customer> customerCollection;
    public static volatile SingularAttribute<Luggage, Customer> customerid;
    public static volatile SingularAttribute<Luggage, Flight> flightid;

}