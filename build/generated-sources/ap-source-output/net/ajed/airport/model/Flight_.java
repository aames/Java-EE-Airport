package net.ajed.airport.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.ajed.airport.model.Customer;
import net.ajed.airport.model.Gate;
import net.ajed.airport.model.Luggage;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-01-05T19:35:37")
@StaticMetamodel(Flight.class)
public class Flight_ { 

    public static volatile SingularAttribute<Flight, Integer> id;
    public static volatile CollectionAttribute<Flight, Customer> customerCollection;
    public static volatile SingularAttribute<Flight, Gate> gateid;
    public static volatile SingularAttribute<Flight, String> destination;
    public static volatile CollectionAttribute<Flight, Luggage> luggageCollection;

}