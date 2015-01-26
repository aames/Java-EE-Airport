package net.ajed.airport.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.ajed.airport.model.Flight;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-01-05T19:35:37")
@StaticMetamodel(Gate.class)
public class Gate_ { 

    public static volatile SingularAttribute<Gate, Integer> id;
    public static volatile CollectionAttribute<Gate, Flight> flightCollection;
    public static volatile SingularAttribute<Gate, String> name;

}